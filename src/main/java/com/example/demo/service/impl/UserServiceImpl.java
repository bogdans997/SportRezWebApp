package com.example.demo.service.impl;

import com.example.demo.exceptions.NullException;
import com.example.demo.exceptions.PageException;
import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.dto.TeamDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.UserTeamDTO;
import com.example.demo.model.entity.TeamEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.mappers.UsersMapper;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.specifications.UsersSpecification;
import com.example.demo.searchOptions.UserSearchOption;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UsersMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    private final JavaMailSender javaMailSender;

    @Autowired
    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public List<UserDTO> findUsers() {
        logger.debug("Listing all users...");
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity:userEntities) {
            userDTOs.add(userMapper.toDto(userEntity));
        }
        return userDTOs;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDTO saveUser(UserDTO user) {


        if((user.getUsername() == null || user.getMail() == null || user.getPassword() == null || user.getRoleId() == null)){
            throw new NullException("Username, email, password and roleId can't be null");
        }

           UserEntity userEntity = new UserEntity();


        if(user.getUsername() != null) {
            if(userRepository.findByUsername(user.getUsername()) != null) {
                throw new ResourceAlreadyExistsException("User with username = " + user.getUsername() + " already exists");
            }
            userEntity.setUsername(user.getUsername());
        }

        if(user.getMail() != null) {
            if(userRepository.findByMail(user.getMail()) != null) {
                throw new ResourceAlreadyExistsException("User with email = " + user.getMail() + " already exists");
            }
            userEntity.setMail(user.getMail());
        }

        if(user.getRoleId() != null) {
            if(Role.getCodeByValue(user.getRoleId()) == null) {
                throw new ResourceNotFoundException("Role with id = " + user.getRoleId() + " not found");
            }
            userEntity.setRoleId(user.getRoleId());
        }
        if(user.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getActive() != null){
            userEntity.setActive(user.getActive());
        }

        return userMapper.toDto(userRepository.save(userEntity));
    }


    @Override
    public UserDTO updateUser(Integer id, UserDTO user) {
        UserEntity userEntity = userMapper.toEntity(user);
        if(id != null){
            userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        }
        if(id == null && (user.getUsername() == null || user.getMail() == null || user.getPassword() == null || user.getRoleId() == null)){
            throw new NullException("Username, email, password and roleId can't be null");
        }

        if(user.getUsername() != null) {
            if(userRepository.findByUsername(user.getUsername()) != null) {
                throw new ResourceAlreadyExistsException("User with username = " + user.getUsername() + " already exists");
            }
            userEntity.setUsername(user.getUsername());
        }

        if(user.getMail() != null) {
            if(userRepository.findByMail(user.getMail()) != null) {
                throw new ResourceAlreadyExistsException("User with email = " + user.getMail() + " already exists");
            }
            userEntity.setMail(user.getMail());
        }

        if(user.getRoleId() != null) {
            if(Role.getCodeByValue(user.getRoleId()) == null) {
                throw new ResourceNotFoundException("Role with id = " + user.getRoleId() + " not found");
            }
            userEntity.setRoleId(user.getRoleId());
        }
        if(id == null || user.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if(user.getActive() != null){
            userEntity.setActive(user.getActive());
        }

        return userMapper.toDto(userRepository.save(userEntity));
    }



    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> userSearch(UserSearchOption options,
                                    Integer pageNumber,
                                    Integer pageSize)
    {
        if(pageNumber <= 0){
            throw new PageException("Page number must be grater than 0");
        }
        if(pageSize <= 0){
            throw new PageException("Page size must be grater than 0");
        }
        Page<UserEntity> userEntities = userRepository.findAll(UsersSpecification.userSearch(options),
                PageRequest.of(pageNumber-1, pageSize));
        List<UserDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity: userEntities) {
            userDTOs.add(userMapper.toDto(userEntity));
        }
        return userDTOs;
    }

    @Override
    public void forgottenPassword(String email) {
        UserEntity user = userRepository.findByMail(email);
        if(user == null){
            throw new ResourceNotFoundException("User with email = " + email + " not found");
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.save(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Password Reset!");
        mailMessage.setFrom("sportskirezultati@gmail.com");
        mailMessage.setText("To complete the password reset process, please click here: http://localhost:8084/user/change-password/" + token);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void changePassword(String token) {
        userRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token = " + token + " not found"));
    }

    @Override
    public void resetPassword(String token, String password) {
        UserEntity userEntity = userRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("User with token = " + token + " not found"));
        userEntity.setToken(null);
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    @Override
    public void addUserInTeam(UserTeamDTO userTeam)
    {
        UserEntity user = userRepository.findById(userTeam.getUserId()).get();
        TeamEntity team = teamRepository.findById(userTeam.getTeamId()).get();

        user.getTeams_users().add(team);

        userRepository.save(user);

    }
}

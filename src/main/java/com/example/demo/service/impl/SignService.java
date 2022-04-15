package com.example.demo.service.impl;

import com.example.demo.model.entity.SportEntity;
import com.example.demo.model.entity.TeamEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repositories.SportRepository;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SignService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    void StartUp(){
        UserEntity user1 = UserEntity.builder()
                .username("Bogdan")
                .mail("bogdan.stankovic@pmf.edu.rs")
                .password(bCryptPasswordEncoder.encode("pass"))
                .roleId(1)
                .active(true)
                .build();
        UserEntity user2 = UserEntity.builder()
                .username("Nikola")
                .mail("nikola@gmail.com")
                .password(bCryptPasswordEncoder.encode("pass"))
                .roleId(1)
                .active(true)
                .build();
        UserEntity user3 = UserEntity.builder()
                .username("Pera")
                .mail("pera@gmail.com")
                .password(bCryptPasswordEncoder.encode("pass"))
                .roleId(1)
                .active(true)
                .build();
        UserEntity user4 = UserEntity.builder()
                .username("Zika")
                .mail("zika@gmail.com")
                .password(bCryptPasswordEncoder.encode("pass"))
                .roleId(2)
                .active(true)
                .build();

        SportEntity sport1 = SportEntity.builder()
                .sportName("Fudbal")
                .build();
        SportEntity sport2 = SportEntity.builder()
                .sportName("Tenis")
                .build();

        TeamEntity team1 = TeamEntity.builder()
                .teamName("Prvi")
                .build();
        TeamEntity team2 = TeamEntity.builder()
                .teamName("Drugi")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        sportRepository.save(sport1);
        sportRepository.save(sport2);
        teamRepository.save(team1);
        teamRepository.save(team2);
    }
}

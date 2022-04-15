package com.example.demo.service;


import com.example.demo.model.dto.TeamDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.UserTeamDTO;
import com.example.demo.searchOptions.UserSearchOption;

import java.util.List;

public interface UserService {

    List<UserDTO> findUsers();
    UserDTO findUserById(Integer id);
    UserDTO saveUser(UserDTO user);
    UserDTO updateUser(Integer id, UserDTO user);
    void deleteUser(Integer id);
    List<UserDTO> userSearch(UserSearchOption options,
                             Integer pageNumber,
                             Integer pageSize);
    void forgottenPassword(String email);
    void changePassword(String token);
    void resetPassword(String token, String password);
    void addUserInTeam(UserTeamDTO userTeam);
}

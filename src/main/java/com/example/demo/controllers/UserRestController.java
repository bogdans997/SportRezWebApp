package com.example.demo.controllers;

import com.example.demo.model.dto.TeamDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.dto.UserTeamDTO;
import com.example.demo.searchOptions.UserSearchOption;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path="/user")
public interface UserRestController
{
    @GetMapping("")
    CollectionModel<EntityModel<UserDTO>> getUsers();

    @GetMapping("/{id}")
    EntityModel<UserDTO> getUser(@PathVariable Integer id);

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<UserDTO> addUser(@RequestBody UserDTO user);


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EntityModel<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO user);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Integer id);

    @PostMapping("/search")
    CollectionModel<EntityModel<UserDTO>> userSearch(@RequestBody UserSearchOption options,
                                                     @RequestParam("page-number") Integer pageNumber,
                                                     @RequestParam("page-size") Integer pageSize);

    @PostMapping("/forgotten-password")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void forgottenPassword(@RequestBody String email);

    @GetMapping("/change-password/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void changePassword(@PathVariable("token") String token);

    @PutMapping("/reset-password/{token}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void resetPassword(@PathVariable String token, @RequestBody String password);

    @PostMapping("/team")
    @ResponseStatus(HttpStatus.CREATED)
    void addUserInTeam(@RequestBody UserTeamDTO userTeam);
}

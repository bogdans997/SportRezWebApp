package com.example.demo.model.dto;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserDTO
{
    private String mail;
    private String username;
    private String password;
    private Integer roleId;
    private Boolean active;
}

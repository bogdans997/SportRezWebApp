package com.example.demo.model.mappers;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UsersMapper
{
    public UserDTO toDto(final UserEntity userEntity) {

        return UserDTO.builder()
                .mail(userEntity.getMail())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roleId(userEntity.getRoleId())
                .active(userEntity.getActive())
                .build();
    }

    public UserEntity toEntity(final UserDTO userDTO) {

        return UserEntity.builder()
                .mail(userDTO.getMail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .roleId(userDTO.getRoleId())
                .active(userDTO.getActive())
                .build();
    }
}

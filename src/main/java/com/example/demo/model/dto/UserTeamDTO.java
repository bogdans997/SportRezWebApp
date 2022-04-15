package com.example.demo.model.dto;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserTeamDTO
{
    Integer userId;
    Integer teamId;
}

package com.example.demo.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TeamDTO
{
    private int teamId;
    private String teamName;
    private String info;
}

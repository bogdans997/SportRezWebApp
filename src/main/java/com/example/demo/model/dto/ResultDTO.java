package com.example.demo.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResultDTO
{
    private int sportId;
    private int team1Id;
    private int team2Id;
    private Integer score1;
    private Integer score2;
    private String info;
}

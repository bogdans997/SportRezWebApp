package com.example.demo.model.dto;

import lombok.*;

@Value
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ResultTeamsDTO
{
    Integer resultId;
    Integer team1Id;
    Integer team2Id;
    Integer sportId;
    Integer score1;
    Integer score2;
}

package com.example.demo.model.mappers;

import com.example.demo.model.dto.TeamDTO;
import com.example.demo.model.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamsMapper
{
    public TeamDTO toDto(final TeamEntity teamEntity){
        return TeamDTO.builder()
                .teamId(teamEntity.getTeamId())
                .teamName(teamEntity.getTeamName())
                .build();
    }

    public TeamEntity toEntity(final TeamDTO teamDTO){
        return TeamEntity.builder()
                .teamId(teamDTO.getTeamId())
                .teamName(teamDTO.getTeamName())
                .build();
    }
}

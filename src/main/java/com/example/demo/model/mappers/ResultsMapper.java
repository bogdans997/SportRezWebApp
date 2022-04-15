package com.example.demo.model.mappers;

import com.example.demo.model.dto.ResultDTO;
import com.example.demo.model.entity.ResultEntity;
import com.example.demo.model.entity.SportEntity;
import com.example.demo.model.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class ResultsMapper
{
    public ResultDTO toDto(final ResultEntity resultEntity){
        return ResultDTO.builder()
                .score1(resultEntity.getScore1())
                .score2(resultEntity.getScore2())
                .team1Id(resultEntity.getTeam1().getTeamId())
                .team2Id(resultEntity.getTeam2().getTeamId())
                .sportId(resultEntity.getSport().getSportId())
                .build();
    }

    public ResultEntity toEntity(final ResultDTO resultDTO, final SportEntity sportEntity, final TeamEntity team1Entity, final TeamEntity team2Entity){
        return ResultEntity.builder()
                .score1(resultDTO.getScore1())
                .score2(resultDTO.getScore2())
                .sport(sportEntity)
                .team1(team1Entity)
                .team2(team2Entity)
                .build();
    }
}

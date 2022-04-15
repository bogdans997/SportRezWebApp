package com.example.demo.model.mappers;

import com.example.demo.model.dto.SportDTO;
import com.example.demo.model.entity.SportEntity;
import org.springframework.stereotype.Component;

@Component
public class SportsMapper
{
    public SportDTO toDto(SportEntity sportEntity){
        return SportDTO.builder()
                .sportId(sportEntity.getSportId())
                .sportName(sportEntity.getSportName())
                .build();
    }

    public SportEntity toEntity(SportDTO sportDTO){
        return SportEntity.builder()
                .sportId(sportDTO.getSportId())
                .sportName(sportDTO.getSportName())
                .build();
    }
}

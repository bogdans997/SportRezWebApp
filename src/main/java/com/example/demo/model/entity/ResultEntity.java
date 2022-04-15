package com.example.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int resultId;

    private Integer score1;
    private Integer score2;


    @ManyToOne
    @JoinColumn(name = "id_sport")
    private SportEntity sport;


    @ManyToOne
    @JoinColumn(name = "id_team1")
    private TeamEntity team1;

    @ManyToOne
    @JoinColumn(name = "id_team2")
    private TeamEntity team2;
}

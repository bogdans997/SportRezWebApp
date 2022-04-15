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
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int teamId;

    private String teamName;


    @ManyToMany(mappedBy = "teams_users")
    private List<UserEntity> users;

    @OneToMany(
            mappedBy = "team1",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ResultEntity> results1;

    @OneToMany(
            mappedBy = "team2",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ResultEntity> results2;
}

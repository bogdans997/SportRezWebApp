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
@Table(name = "sports")
@NoArgsConstructor
@AllArgsConstructor
public class SportEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int sportId;
    @Column
    String sportName;


    @OneToMany(
            mappedBy = "sport",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ResultEntity> results;
}

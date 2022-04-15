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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int user_id;

    private String username;
    private String mail;
    private String password;
    private Integer roleId;
    private Boolean active;
    private String token;

    @PrePersist
    @PreUpdate
    private void prepare(){
        if(active == null){
            active = false;
        }
    }

    @ManyToMany
    @JoinTable(
            name = "users_teams",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "teams_id"))
    private List<TeamEntity> teams_users;
}

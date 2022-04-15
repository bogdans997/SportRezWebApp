package com.example.demo.service.impl;

import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ErrorInfo;
import com.example.demo.exceptions.NullException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.TeamDTO;
import com.example.demo.model.entity.TeamEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.mappers.TeamsMapper;
import com.example.demo.model.mappers.UsersMapper;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService
{
    private final TeamRepository teamRepository;

    private final TeamsMapper teamsMapper;

    private final UserRepository userRepository;

    public List<TeamDTO> getAllTeams(){
        return teamRepository.findAll().stream().map(teamsMapper::toDto).collect(Collectors.toList());
    }

    public TeamDTO getTeam(final int id) throws ResourceNotFoundException {

        final TeamEntity teamEntity= teamRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Team with id: " + id + " not found"));

        return teamsMapper.toDto(teamEntity);
    }

    public void deleteTeam(final int id) throws ResourceNotFoundException {

        if(!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Team with id: " + id + " not exists");
        }

        teamRepository.deleteById(id);
    }

    public TeamDTO addTeam(final TeamDTO team)throws DuplicateResourceException, ResourceNotFoundException {

        final TeamEntity teamEntity= teamsMapper.toEntity(team);

        if(team.getTeamName() == null){
            throw new NullException("TeamName can't be null");
        }

        final TeamEntity savedEntity = teamRepository.save(teamEntity);

        return teamsMapper.toDto(savedEntity);
    }

    public TeamDTO updateTeam(final int id, final TeamDTO teamDTO) throws ResourceNotFoundException {

        final TeamEntity teamEntity= teamRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Team with id: " + id + " not exists"));

        if(teamDTO.getTeamName() == null){
            throw new NullException("TeamName can't be null");
        }

        teamEntity.setTeamName(teamDTO.getTeamName());

        final TeamEntity saveEntity= teamRepository.save(teamEntity);

        return teamsMapper.toDto(saveEntity);
    }

    public TeamDTO teamInfo(final int id) throws ResourceNotFoundException {

        final TeamEntity teamEntity= teamRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Team with id: " + id + " not exists"));

        TeamDTO team = teamsMapper.toDto(teamEntity);

        List<UserEntity> players = teamEntity.getUsers();
        List<String> playersNames = new ArrayList<>();
        for(UserEntity x: players){
            playersNames.add(x.getUsername());
        }

        team.setInfo("Team: " + team.getTeamName() + " Players: " + playersNames );

        return team;
    }
}

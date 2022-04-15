package com.example.demo.controllers.impl;

import com.example.demo.controllers.TeamRestController;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.TeamDTO;
import com.example.demo.service.impl.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamRestControllerImpl implements TeamRestController
{
    private final TeamService teamService;

    @Override
    public List<TeamDTO> getAllTeams() {
        // TODO Auto-generated method stub
        return teamService.getAllTeams();
    }

    @Override
    public TeamDTO addTeam(TeamDTO team)throws DuplicateResourceException, ResourceNotFoundException {
        // TODO Auto-generated method stub
        return teamService.addTeam(team);
    }

    @Override
    public TeamDTO getTeam(final int id)throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return teamService.getTeam(id);
    }

    @Override
    public void deleteTeam(int id) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        teamService.deleteTeam(id);
    }

    @Override
    public TeamDTO updateTeam(int id, TeamDTO team) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return teamService.updateTeam(id, team);
    }

    @Override
    public TeamDTO getInfoTeam(int id) throws ResourceNotFoundException{
        return teamService.teamInfo(id);
    }
}

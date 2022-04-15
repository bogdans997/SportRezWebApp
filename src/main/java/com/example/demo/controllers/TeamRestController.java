package com.example.demo.controllers;

import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.TeamDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/teams")
public interface TeamRestController
{
    @RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List<TeamDTO> getAllTeams();

    @RequestMapping(method=RequestMethod.GET, path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    TeamDTO getTeam(@PathVariable(name="id") int id) throws ResourceNotFoundException;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST, path="", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    TeamDTO addTeam(@RequestBody TeamDTO team) throws DuplicateResourceException, ResourceNotFoundException;

    @RequestMapping(method = RequestMethod.DELETE, path="/{id}")
    void deleteTeam(@PathVariable(name="id")int id) throws ResourceNotFoundException;

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    TeamDTO updateTeam(@PathVariable(name = "id")int id, @RequestBody TeamDTO team) throws ResourceNotFoundException;

    @RequestMapping(method=RequestMethod.GET, path="/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    TeamDTO getInfoTeam(@PathVariable(name="id") int id) throws ResourceNotFoundException;
}

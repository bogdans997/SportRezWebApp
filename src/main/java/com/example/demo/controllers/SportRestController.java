package com.example.demo.controllers;

import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.SportDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/sports")
public interface SportRestController
{
    @RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SportDTO> getAllSports();

    @RequestMapping(method=RequestMethod.GET, path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    SportDTO getSport(@PathVariable(name="id") int id) throws ResourceNotFoundException;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST, path="", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    SportDTO addSport(@RequestBody SportDTO sport) throws DuplicateResourceException, ResourceNotFoundException;

    @RequestMapping(method = RequestMethod.DELETE, path="/{id}")
    void deleteSport(@PathVariable(name="id")int id) throws ResourceNotFoundException;

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    SportDTO updateSport(@PathVariable(name = "id")int id, @RequestBody SportDTO sport) throws ResourceNotFoundException;
}

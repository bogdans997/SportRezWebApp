package com.example.demo.controllers;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.ResultDTO;
import com.example.demo.model.dto.ResultTeamsDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.searchOptions.ResultSearchOption;
import com.example.demo.searchOptions.UserSearchOption;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "results")
public interface ResultRestController
{
    @RequestMapping(method = RequestMethod.GET, path = "", produces = MediaType.APPLICATION_JSON_VALUE)

    Page<ResultDTO> getAllResults(
            @RequestParam(name = "page", required = false)final Integer page,
            @RequestParam(name = "page_size", required = false)final Integer page_size);

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultDTO getResult(@PathVariable(name = "id")int id) throws ResourceNotFoundException;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResultDTO addResult(@RequestBody ResultDTO result) throws DuplicateResourceException, ResourceNotFoundException, BadRequestException;

    @RequestMapping(method = RequestMethod.DELETE, path="/{id}")
    void deleteResult(@PathVariable(name="id")int id) throws ResourceNotFoundException;

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultDTO updateResult(@PathVariable(name = "id")int id, @RequestBody ResultDTO result) throws ResourceNotFoundException;

    @PostMapping("/info")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void addResultInfo(@RequestBody ResultTeamsDTO info);

    @PostMapping("/search")
    CollectionModel<EntityModel<ResultDTO>> resultSearch(@RequestBody ResultSearchOption options,
                                                     @RequestParam("page-number") Integer pageNumber,
                                                     @RequestParam("page-size") Integer pageSize);

    @GetMapping("2")
    CollectionModel<EntityModel<ResultDTO>> getResults();
}

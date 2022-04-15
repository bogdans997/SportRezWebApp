package com.example.demo.controllers.impl;

import com.example.demo.controllers.ResultRestController;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.ResultSearchOptions;
import com.example.demo.model.dto.ResultDTO;
import com.example.demo.model.dto.ResultTeamsDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.searchOptions.ResultSearchOption;
import com.example.demo.searchOptions.UserSearchOption;
import com.example.demo.service.impl.ResultService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class ResultRestControllerImpl implements ResultRestController
{
    final ResultService resultService;

    private static final Logger logger = LoggerFactory.getLogger(ResultRestControllerImpl.class);

    @Override
    public Page<ResultDTO> getAllResults(
            final Integer page,
            final Integer page_size) {
        // TODO Auto-generated method stub
        final ResultSearchOptions searchResult= ResultSearchOptions.builder()
                .page(page)
                .page_size(page_size)
                .build();

        return resultService.getAllResults(searchResult);
    }

    @Override
    public CollectionModel<EntityModel<ResultDTO>> getResults() {
        logger.info("URL: ../user");
        logger.debug("Getting all users...");
        List<EntityModel<ResultDTO>> models = new ArrayList<>();
        List<ResultDTO> results = resultService.findResults();
        for (ResultDTO result:results) {

            models.add(EntityModel.of(result));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(ResultRestControllerImpl.class).getResults()).withSelfRel());
    }

    @Override
    public ResultDTO getResult(int id) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return resultService.getResult(id);
    }

    @Override
    public ResultDTO addResult(ResultDTO result) throws DuplicateResourceException, ResourceNotFoundException, BadRequestException {
        // TODO Auto-generated method stub
        return resultService.addResult(result);
    }

    @Override
    public void deleteResult(int id) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        resultService.deleteResult(id);
    }

    @Override
    public ResultDTO updateResult(int id, ResultDTO result) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return resultService.upadateResult(id, result);
    }

    @Override
    public void addResultInfo(ResultTeamsDTO info){
       resultService.addResultInfo(info);
    }


    @Override
    public CollectionModel<EntityModel<ResultDTO>> resultSearch(ResultSearchOption options,
                                                                Integer pageNumber,
                                                                Integer pageSize){
        logger.info("URL: ../user/search");
        logger.debug("Searching users...");
        List<EntityModel<ResultDTO>> models = new ArrayList<>();
        List<ResultDTO> results = resultService.resultSearch(options, pageNumber, pageSize);
        for (ResultDTO resultDTO:results) {
            models.add(EntityModel.of(resultDTO));
        }

        return CollectionModel.of(models,
                linkTo(methodOn(ResultRestControllerImpl.class).getResults()).withSelfRel());

    }
}

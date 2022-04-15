package com.example.demo.service.impl;

import com.example.demo.exceptions.*;
import com.example.demo.model.ResultSearchOptions;
import com.example.demo.model.dto.ResultDTO;
import com.example.demo.model.dto.ResultTeamsDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.ResultEntity;
import com.example.demo.model.entity.SportEntity;
import com.example.demo.model.entity.TeamEntity;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.mappers.ResultsMapper;
import com.example.demo.repositories.ResultRepository;
import com.example.demo.repositories.SportRepository;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.specifications.ResultSearchSpecification;
import com.example.demo.repositories.specifications.ResultSpecification;
import com.example.demo.repositories.specifications.UsersSpecification;
import com.example.demo.searchOptions.ResultSearchOption;
import com.example.demo.searchOptions.UserSearchOption;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService
{
    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private final ResultRepository resultRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final SportRepository sportRepository;

    private final ResultsMapper resultsMapper;

    private static final Logger logger = LoggerFactory.getLogger(ResultService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Page<ResultDTO> getAllResults(final ResultSearchOptions searchResult) {

        final PageRequest pageRequest;

        if (searchResult.getPage() != null) {
            pageRequest = PageRequest.of(searchResult.getPage(),
                    searchResult.getPage_size() != null ? searchResult.getPage_size() : DEFAULT_PAGE_SIZE);
        } else {
            pageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        }

        return resultRepository.findAll(new ResultSearchSpecification(searchResult), pageRequest)
                .map((resultEntity -> {
                    ResultDTO result = resultsMapper.toDto(resultEntity);
                    result.setInfo(resultEntity.getSport().getSportName() + ": " + resultEntity.getTeam1().getTeamName() + " " +
                            resultEntity.getScore1() + " : " +
                            resultEntity.getScore2() + " " + resultEntity.getTeam2().getTeamName());
                    return  result;
                }));
    }

    public ResultDTO getResult(final int id) throws ResourceNotFoundException {
        final ResultEntity resultEntity = resultRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Result with id: " + id + " not exists"));
        ResultDTO result = resultsMapper.toDto(resultEntity);
        result.setInfo(resultEntity.getSport().getSportName() + ": " + resultEntity.getTeam1().getTeamName() + " " +
                resultEntity.getScore1() + " : " +
                resultEntity.getScore2() + " " + resultEntity.getTeam2().getTeamName());
        return result;
    }

    public List<ResultDTO> findResults() {
        logger.debug("Listing all results...");
        List<ResultEntity> resultEntities = resultRepository.findAll();
        List<ResultDTO> resultDTOs = new ArrayList<>();
        for (ResultEntity resultEntity:resultEntities) {
            resultDTOs.add(resultsMapper.toDto(resultEntity));
        }
        return resultDTOs;
    }

    public ResultDTO addResult(final ResultDTO resultDTO)
            throws ResourceAlreadyExistsException, ResourceNotFoundException, BadRequestException {

        final int sport_id = resultDTO.getSportId();

        final Integer team1_id = resultDTO.getTeam1Id();
        final Integer team2_id = resultDTO.getTeam2Id();

        final TeamEntity team1Entity;
        final TeamEntity team2Entity;

        if(team1_id == team2_id){
            throw new ResourceAlreadyExistsException("Team1 and team2 must be different");
        }


        final SportEntity sportEntity = sportRepository.findById(sport_id)
                .orElseThrow(() -> new ResourceNotFoundException("Sport with id: " + sport_id + " not exists"));

        if (team1_id != 0) {
            team1Entity = teamRepository.findById(team1_id)
                    .orElseThrow(() -> new ResourceNotFoundException("Team 1 with id: " + team1_id + " not exists"));
        } else {
            team1Entity = null;
            ;
        }

        if (team2_id != 0) {
            team2Entity = teamRepository.findById(team2_id)
                    .orElseThrow(() -> new ResourceNotFoundException("Team 2 with id: " + team2_id + " not exists"));
        } else {
            team2Entity = null;
            ;
        }

        final ResultEntity resultEntity = resultsMapper.toEntity(resultDTO, sportEntity, team1Entity, team2Entity);
        final ResultEntity saveResult = resultRepository.save(resultEntity);

        return resultsMapper.toDto(saveResult);
    }

    public void deleteResult(final int id) throws ResourceNotFoundException {

        if (!resultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Result with id: " + id + " not exists");
        }

        resultRepository.deleteById(id);
    }

    public ResultDTO upadateResult(final int id, final ResultDTO resultDTO) throws ResourceNotFoundException {

        final ResultEntity resultEntity = resultRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Result with id: " + id + " not exists"));


        resultEntity.setScore1(resultDTO.getScore1());
        resultEntity.setScore2(resultDTO.getScore2());


        final ResultEntity saveEntity = resultRepository.save(resultEntity);

        return resultsMapper.toDto(saveEntity);
    }

    public void addResultInfo(ResultTeamsDTO info) throws ResourceAlreadyExistsException{

        ResultEntity result = resultRepository.findById(info.getResultId()).get();
        TeamEntity team1 = teamRepository.findById(info.getTeam1Id()).get();
        TeamEntity team2 = teamRepository.findById(info.getTeam2Id()).get();
        SportEntity sport = sportRepository.findById(info.getSportId()).get();

        if(team1.getTeamId() == team2.getTeamId()){
            throw new ResourceAlreadyExistsException("Team1 and team2 must be different");
        }

        result.setTeam1(team1);
        result.setTeam2(team2);
        result.setSport(sport);
        result.setScore1(info.getScore1());
        result.setScore2(info.getScore2());

        resultRepository.save(result);

    }

    public List<ResultDTO> resultSearch(ResultSearchOption options,
                                        Integer pageNumber,
                                        Integer pageSize)
    {
        if(pageNumber <= 0){
            throw new PageException("Page number must be grater than 0");
        }
        if(pageSize <= 0){
            throw new PageException("Page size must be grater than 0");
        }
        Page<ResultEntity> resultEntities = resultRepository.findAll(ResultSpecification.resultSearch(options),
                PageRequest.of(pageNumber-1, pageSize));
        List<ResultDTO> resultDTOs = new ArrayList<>();
        for (ResultEntity resultEntity: resultEntities) {
            resultDTOs.add(resultsMapper.toDto(resultEntity));
        }
        return resultDTOs;
    }

}

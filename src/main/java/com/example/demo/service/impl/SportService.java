package com.example.demo.service.impl;

import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ErrorInfo;
import com.example.demo.exceptions.NullException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.SportDTO;
import com.example.demo.model.entity.SportEntity;
import com.example.demo.model.mappers.SportsMapper;
import com.example.demo.repositories.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportService
{
    private final SportRepository sportRepository;
    //private final UserRepository userRepository;

    private final SportsMapper sportsMapper;

    public List<SportDTO> getAllSports(){

        return sportRepository.findAll().stream().map(sportsMapper::toDto).collect(Collectors.toList());
    }

    public SportDTO addSport(final SportDTO sport)throws DuplicateResourceException, ResourceNotFoundException {

        final SportEntity sportEntity= sportsMapper.toEntity(sport);

        if(sport.getSportName() == null){
            throw new NullException("SportName can't be null");
        }

        final SportEntity savedEntity = sportRepository.save(sportEntity);

        return sportsMapper.toDto(savedEntity);
    }

    public SportDTO getSport(final int id) throws ResourceNotFoundException {

        final SportEntity sportEntity= sportRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Sport with id: " + id + " not found"));

        return sportsMapper.toDto(sportEntity);
    }

    public void deleteSport(final int id) throws ResourceNotFoundException {

        if(!sportRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sport with id: " + id + " not exists");
        }

        sportRepository.deleteById(id);
    }

    public SportDTO updateSport(final int id, final SportDTO sportDTO) throws ResourceNotFoundException {

        final SportEntity sportEntity= sportRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Sport with id: " + id + " not exists"));

        if(sportDTO.getSportName() == null){
            throw new NullException("TeamName can't be null");
        }

        sportEntity.setSportName(sportDTO.getSportName());

        final SportEntity saveEntity= sportRepository.save(sportEntity);

        return sportsMapper.toDto(saveEntity);
    }
}

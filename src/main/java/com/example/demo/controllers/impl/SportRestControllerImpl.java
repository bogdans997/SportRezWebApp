package com.example.demo.controllers.impl;

import com.example.demo.controllers.SportRestController;
import com.example.demo.exceptions.DuplicateResourceException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.dto.SportDTO;
import com.example.demo.service.impl.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SportRestControllerImpl implements SportRestController
{
    private final SportService sportService;

    @Override
    public List<SportDTO> getAllSports() {
        // TODO Auto-generated method stub
        return sportService.getAllSports();
    }

    @Override
    public SportDTO addSport(SportDTO sport)throws DuplicateResourceException, ResourceNotFoundException {
        // TODO Auto-generated method stub
        return sportService.addSport(sport);
    }

    @Override
    public SportDTO getSport(final int id)throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return sportService.getSport(id);
    }

    @Override
    public void deleteSport(int id) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        sportService.deleteSport(id);
    }

    @Override
    public SportDTO updateSport(int id, SportDTO sport) throws ResourceNotFoundException {
        // TODO Auto-generated method stub
        return sportService.updateSport(id, sport);
    }
}

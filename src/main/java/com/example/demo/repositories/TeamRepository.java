package com.example.demo.repositories;

import com.example.demo.model.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Integer>
{
    @Override
    List<TeamEntity> findAll();
}

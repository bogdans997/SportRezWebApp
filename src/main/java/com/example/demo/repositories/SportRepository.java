package com.example.demo.repositories;

import com.example.demo.model.entity.SportEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends CrudRepository<SportEntity, Integer>
{
    @Override
    List<SportEntity> findAll();
}

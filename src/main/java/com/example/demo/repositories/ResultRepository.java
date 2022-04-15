package com.example.demo.repositories;

import com.example.demo.model.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends PagingAndSortingRepository<ResultEntity, Integer>, JpaSpecificationExecutor<ResultEntity>
{
    @Override
    List<ResultEntity> findAll();
}

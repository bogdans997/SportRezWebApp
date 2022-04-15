package com.example.demo.repositories;

import com.example.demo.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity>
{
    @Override
    List<UserEntity> findAll();

    UserEntity findByUsername(String username);
    UserEntity findByMail(String email);
    Optional<UserEntity> findByToken(String token);
}

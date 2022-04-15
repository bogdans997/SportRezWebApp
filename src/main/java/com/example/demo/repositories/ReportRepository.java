package com.example.demo.repositories;

import com.example.demo.model.dto.UserReportDTO;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserEntity_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReportRepository
{
    @Autowired
    EntityManager entityManager;

    public List<UserReportDTO> reportUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserReportDTO> query = criteriaBuilder.createQuery(UserReportDTO.class);
        Root<UserEntity> root = query.from(UserEntity.class);

        query.multiselect(
                root.get(UserEntity_.roleId),
                criteriaBuilder.count(root.get(UserEntity_.user_id)));

        query.groupBy(root.get(UserEntity_.roleId));

        return entityManager.createQuery(query).getResultList();
    }
}

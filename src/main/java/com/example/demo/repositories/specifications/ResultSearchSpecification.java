package com.example.demo.repositories.specifications;

import com.example.demo.model.ResultSearchOptions;
import com.example.demo.model.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ResultSearchSpecification implements Specification<ResultEntity>
{
    private static final long serialVersionUID = 1L;

    private final ResultSearchOptions resultSearch;

    @Override
    public Predicate toPredicate(Root<ResultEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub

        final List<Predicate> predicate = new ArrayList<>();


        return criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));
    }
}

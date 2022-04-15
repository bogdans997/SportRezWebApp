package com.example.demo.repositories.specifications;

import com.example.demo.model.Role;
import com.example.demo.model.entity.*;
import com.example.demo.searchOptions.ResultSearchOption;
import com.example.demo.searchOptions.UserSearchOption;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ResultSpecification
{
    public static Specification<ResultEntity> resultSearch(ResultSearchOption options)
    {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(options.getSport())){
                Path<String> sport = root.get(ResultEntity_.sport).get(SportEntity_.sportName);
                predicates.add(criteriaBuilder.like(sport, "%"+ options.getSport() +"%"));
            }

            if(StringUtils.hasText(options.getTeam())){
                Path<String> team = root.get(ResultEntity_.team1).get(TeamEntity_.teamName);
                predicates.add(criteriaBuilder.like(team, "%"+ options.getTeam() +"%"));
            }

            if(StringUtils.hasText(options.getTeam())){
                Path<String> team = root.get(ResultEntity_.team2).get(TeamEntity_.teamName);
                predicates.add(criteriaBuilder.like(team, "%"+ options.getTeam() +"%"));
            }


            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]) );
        });
    }
}

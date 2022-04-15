package com.example.demo.repositories.specifications;

import com.example.demo.model.Role;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.entity.UserEntity_;
import com.example.demo.searchOptions.UserSearchOption;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UsersSpecification {

    public static Specification<UserEntity> userSearch(UserSearchOption options)
    {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(options.getUsername())){
                Path<String> username = root.get(UserEntity_.username);
                predicates.add(criteriaBuilder.like(username, "%"+ options.getUsername() +"%"));
            }

            if(StringUtils.hasText(options.getEmail())){
                Path<String> email = root.get(UserEntity_.mail);
                predicates.add(criteriaBuilder.like(email, "%"+ options.getEmail() +"%"));
            }

            if(options.getRole() != null){
                Path<Integer> role = root.get(UserEntity_.roleId);
                predicates.add(criteriaBuilder.equal(role, Role.getValueByCode(options.getRole())));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]) );
        });
    }
}

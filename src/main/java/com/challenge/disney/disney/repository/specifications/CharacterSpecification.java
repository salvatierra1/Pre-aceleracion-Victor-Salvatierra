package com.challenge.disney.disney.repository.specifications;


import com.challenge.disney.disney.dto.CharacterFiltersDTO;
import com.challenge.disney.disney.entity.MovieEntity;
import com.challenge.disney.disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.util.StringUtils.hasLength;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getFilters(CharacterFiltersDTO characterFiltersDTO) {

        //=== Lambda ===
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //=== name ===
            if (hasLength(characterFiltersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFiltersDTO.getName().toLowerCase() + "%"
                        )
                );
            }
            //=== Age ===
            if (characterFiltersDTO.getAge() != null) predicates.add(
                    criteriaBuilder.equal(root.get("age"), characterFiltersDTO.getAge())
            );

            //=== Movies ===

            if (!CollectionUtils.isEmpty(characterFiltersDTO.getMovies())) {
                Join<CharacterEntity, MovieEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(characterFiltersDTO.getMovies()));
            }
                query.distinct(true);
                query.orderBy(criteriaBuilder.asc(root.get("name"))
                );
                return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
            };

        }
    }
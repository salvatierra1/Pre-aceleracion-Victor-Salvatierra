package com.challenge.disney.disney.repository.specifications;


import com.challenge.disney.disney.dto.PersonajeFiltersDTO;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.entity.PersonajeEntity;
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
public class PersonajeSpecification {

    public Specification<PersonajeEntity> getFilters(PersonajeFiltersDTO personajeFilters){

        //=== Lambda ===
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //=== name ===
           if (hasLength(personajeFilters.getName())) {
               predicates.add(
                       criteriaBuilder.like(
                               criteriaBuilder.lower(root.get("name")),
                               "%" + personajeFilters.getName().toLowerCase() + "%"
                       )
               );
           }
           //=== Age ===
            if(personajeFilters.getAge() != null) predicates.add(
                    criteriaBuilder.equal(root.get("age"), personajeFilters.getAge())
            );

            //=== Movies ===
            if (!CollectionUtils.isEmpty(personajeFilters.getMovies())){
                Join<PersonajeEntity, PeliculaEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(personajeFilters.getMovies()));
            }
            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("name")));
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }
}

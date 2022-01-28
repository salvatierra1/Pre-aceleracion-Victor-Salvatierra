package com.challenge.disney.disney.auth.repository.specifications;


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
           if (hasLength(personajeFilters.getNombre())) {
               predicates.add(
                       criteriaBuilder.like(
                               criteriaBuilder.lower(root.get("nombre")),
                               "%" + personajeFilters.getNombre().toLowerCase() + "%"
                       )
               );
           }
           //=== Age ===
            if(personajeFilters.getEdad() != null) predicates.add(
                    criteriaBuilder.equal(root.get("edad"), personajeFilters.getEdad())
            );

            //=== Movies ===
            if (!CollectionUtils.isEmpty(personajeFilters.getPeliculas())){
                Join<PersonajeEntity, PeliculaEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(personajeFilters.getPeliculas()));
            }
            query.distinct(true);
            query.orderBy(criteriaBuilder.asc(root.get("nombre")));
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));
        };
    }
}

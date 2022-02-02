package com.challenge.disney.disney.repository.specifications;

import com.challenge.disney.disney.dto.MovieFiltersDTO;
import com.challenge.disney.disney.entity.GenderEntity;
import com.challenge.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> obtenerFiltro(MovieFiltersDTO movieFiltersDTO){

        //=== Lambda ===
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //=== Name ==
            if (StringUtils.hasLength(movieFiltersDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + movieFiltersDTO.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            //=== Date ===
            if (StringUtils.hasLength(movieFiltersDTO.getDate())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(movieFiltersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.get("fechaCreacion"), date)
                );
            }

            //=== Genre ===
            if (!CollectionUtils.isEmpty(movieFiltersDTO.getGenero())) {
                Join<MovieEntity, GenderEntity> join = root.join("genero", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(movieFiltersDTO.getGenero()));
            }

            query.distinct(true);

            //=== Order ===
            String orderByField = "titulo";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

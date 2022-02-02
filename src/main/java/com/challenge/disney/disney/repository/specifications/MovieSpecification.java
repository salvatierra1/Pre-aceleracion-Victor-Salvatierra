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
<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/repository/specifications/PeliculaSpecification.java
            if (StringUtils.hasLength(peliculaFiltersDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + peliculaFiltersDTO.getTitle().toLowerCase() + "%"
=======
            if (StringUtils.hasLength(movieFiltersDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + movieFiltersDTO.getTitulo().toLowerCase() + "%"
>>>>>>> develop:src/main/java/com/challenge/disney/disney/repository/specifications/MovieSpecification.java
                        )
                );
            }

            //=== Date ===
            if (StringUtils.hasLength(movieFiltersDTO.getDate())){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(movieFiltersDTO.getDate(), formatter);

                predicates.add(
                        criteriaBuilder.equal(root.get("dateCreation"), date)
                );
            }

            //=== Genre ===
<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/repository/specifications/PeliculaSpecification.java
            if (!CollectionUtils.isEmpty(peliculaFiltersDTO.getGender())) {
                Join<PeliculaEntity, GeneroEntity> join = root.join("genderEntity", JoinType.INNER);
                Expression<String> genderId = join.get("id");
                predicates.add(genderId.in(peliculaFiltersDTO.getGender()));
=======
            if (!CollectionUtils.isEmpty(movieFiltersDTO.getGenero())) {
                Join<MovieEntity, GenderEntity> join = root.join("genero", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(movieFiltersDTO.getGenero()));
>>>>>>> develop:src/main/java/com/challenge/disney/disney/repository/specifications/MovieSpecification.java
            }

            query.distinct(true);

            //=== Order ===
            String orderByField = "title";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

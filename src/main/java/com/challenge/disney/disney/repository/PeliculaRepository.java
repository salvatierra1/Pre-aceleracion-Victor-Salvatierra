package com.challenge.disney.disney.repository;

import com.challenge.disney.disney.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long>, JpaSpecificationExecutor<PeliculaEntity> {

    List<PeliculaEntity> findAll(Specification<PeliculaEntity> specification);

}

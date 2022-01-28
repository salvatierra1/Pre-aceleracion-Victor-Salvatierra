package com.challenge.disney.disney.repository;

import com.challenge.disney.disney.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity, Long> {
}

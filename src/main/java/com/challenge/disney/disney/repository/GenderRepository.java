package com.challenge.disney.disney.repository;

import com.challenge.disney.disney.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}

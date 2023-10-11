package com.example.educationtop.repositories;

import com.example.educationtop.entities.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, String> {
    ScoreEntity findByRut(String rut);
}

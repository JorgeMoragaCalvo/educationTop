package com.example.educationtop.repositories;

import com.example.educationtop.entities.TuitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionRepository extends JpaRepository<TuitionEntity, String> {

    @Query("select t from TuitionEntity t where t.rut = ?1")
    TuitionEntity findByRut(String rut);
}

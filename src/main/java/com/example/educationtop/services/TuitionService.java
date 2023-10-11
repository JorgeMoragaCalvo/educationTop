package com.example.educationtop.services;

import com.example.educationtop.entities.TuitionEntity;
import com.example.educationtop.repositories.TuitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuitionService {

    private final TuitionRepository tuitionRepository;

    @Autowired
    public TuitionService(TuitionRepository tuitionRepository){
        this.tuitionRepository = tuitionRepository;
    }

    public TuitionEntity createRegistrations(TuitionEntity tuitionEntity){
        return tuitionRepository.save(tuitionEntity);
    }

    public List<TuitionEntity> allRegistrations(){
        return tuitionRepository.findAll();
    }
}

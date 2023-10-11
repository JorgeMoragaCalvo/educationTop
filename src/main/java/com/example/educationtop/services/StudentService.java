package com.example.educationtop.services;

import com.example.educationtop.entities.StudentEntity;
import com.example.educationtop.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> allStudents(){
        return studentRepository.findAll();
    }

    public StudentEntity createStudents(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }
}

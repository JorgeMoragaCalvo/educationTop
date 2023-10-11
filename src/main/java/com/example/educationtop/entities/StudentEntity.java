package com.example.educationtop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

    @Id
    private String rut;

    private String lastName;
    private String name;
    private String birthDate;
    private String typeSchool;
    private String schoolName;
    private Integer graduationYear;
}

package com.example.educationtop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreEntity {

    @Id
    private String rut;

    private int totalExams;
    private double averageExams;
}

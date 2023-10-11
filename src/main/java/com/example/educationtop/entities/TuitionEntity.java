package com.example.educationtop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tuition")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuitionEntity {

    @Id
    private String rut;

    private String registrationDate;
    private String paymentType;

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @OneToOne
    @JoinColumn(name = "score_id")
    private ScoreEntity scoreEntity;
}

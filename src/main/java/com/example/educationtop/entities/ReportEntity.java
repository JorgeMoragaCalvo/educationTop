package com.example.educationtop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String rut;
    private String name;
    private String lastName;
    private String paymentType;
    private Integer dues;
    private Integer discount;
    private Integer totalPay;
    private Integer totalExams;
    private Double averageScore;

    @OneToOne
    @JoinColumn(name = "tuition_id")
    private TuitionEntity tuitionEntity;
}

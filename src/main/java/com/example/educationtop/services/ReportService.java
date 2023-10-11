package com.example.educationtop.services;

import com.example.educationtop.entities.ReportEntity;
import com.example.educationtop.entities.ScoreEntity;
import com.example.educationtop.entities.StudentEntity;
import com.example.educationtop.entities.TuitionEntity;
import com.example.educationtop.repositories.ReportRepository;
import com.example.educationtop.repositories.TuitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService {

    @Autowired
    private TuitionRepository tuitionRepository;

    @Autowired
    private ReportRepository reportRepository;

    private static final int TARIFF_AMOUNT = 1500000;
    private static final int TUITION_AMOUNT = 70000;

    @Transactional
    public void calculateAndSaveDiscount(String rut){
        TuitionEntity tuitionEntity = tuitionRepository.findByRut(rut);
        StudentEntity studentEntity = tuitionEntity.getStudentEntity();
        ScoreEntity scoreEntity = tuitionEntity.getScoreEntity();

        double discount;

        int dues = calculateDues(studentEntity);
        //int tariff = 1500000;
        //int tuition = 70000;
        int total, dto;

        ReportEntity reportEntity = new ReportEntity();

        if(tuitionEntity.getPaymentType().equals("Contado")){
            discount = 0.5;
        } else {
            discount = calculateDiscountBySchoolType(studentEntity) +
                    calculateDiscountByGraduationYear(studentEntity) +
                    calculateDiscountByAverageScore(scoreEntity);

        }
        dto = (int) (discount * 100);
        total = (int) ((TARIFF_AMOUNT + TUITION_AMOUNT) * (1 - discount));

        reportEntity.setDiscount(dto);
        reportEntity.setName(studentEntity.getName());
        reportEntity.setLastName(studentEntity.getLastName());
        reportEntity.setDues(dues);
        reportEntity.setRut(studentEntity.getRut());
        reportEntity.setPaymentType(tuitionEntity.getPaymentType());
        reportEntity.setTotalPay(total);
        reportEntity.setTotalExams(scoreEntity.getTotalExams());
        reportEntity.setAverageScore(scoreEntity.getAverageExams());

        reportRepository.save(reportEntity);
    }

    public Double calculateDiscountBySchoolType(StudentEntity studentEntity){
        String schoolType = studentEntity.getTypeSchool();

        return switch (schoolType) {
            case "Municipal" -> 0.2;
            case "Subvencionado" -> 0.1;
            default -> 0.0;
        };
    }

    public Double calculateDiscountByGraduationYear(StudentEntity studentEntity){
        int year = studentEntity.getGraduationYear();
        double discount;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int discountByYear = currentYear - year;

        if(discountByYear < 1) discount = 0.15;
        else if(discountByYear <=2) discount = 0.08;
        else if(discountByYear >= 3 && discountByYear <=4) discount = 0.04;
        else discount = 0.0;

        return discount;
    }

    public Integer calculateDues(StudentEntity studentEntity){
        String dues = studentEntity.getTypeSchool();

        return switch (dues) {
            case "Municipal" -> 10;
            case "Subvencionado" -> 7;
            default -> 4;
        };
    }

    public Double calculateDiscountByAverageScore(ScoreEntity scoreEntity){
        double averageScore = scoreEntity.getAverageExams();
        double discount = 0.0;
        if(averageScore >= 950 && averageScore <= 1000) discount = 0.1;
        else if(averageScore >= 900 && averageScore < 950) discount = 0.05;
        else if(averageScore >= 850 && averageScore < 900) discount = 0.02;
        else if(averageScore < 850) discount = 0.0;

        return discount;
    }

    public ReportEntity getReportByRut(String rut) {
        return reportRepository.findByRut(rut);
    }
}

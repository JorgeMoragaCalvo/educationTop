package com.example.educationtop;

import com.example.educationtop.entities.ReportEntity;
import com.example.educationtop.entities.ScoreEntity;
import com.example.educationtop.entities.StudentEntity;
import com.example.educationtop.entities.TuitionEntity;
import com.example.educationtop.repositories.ReportRepository;
import com.example.educationtop.repositories.TuitionRepository;
import com.example.educationtop.services.ReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

public class ReportTest {

    ReportService reportService = new ReportService();

    private TuitionRepository tuitionRepository;

    private ReportRepository reportRepository;

    //@Before("")
    //public void setUp() {
    //    MockitoAnnotations.initMocks(this);
    //}

    @Test
    public void testCalculateDiscountBySchoolType() {
        StudentEntity student = new StudentEntity();
        //StudentEntity student1 = new StudentEntity();
        student.setTypeSchool("Municipal");
        //student1.setTypeSchool("Subvencionado");

        double discount = reportService.calculateDiscountBySchoolType(student);
        //double discount1 = reportService.calculateDiscountBySchoolType(student1);

        assertEquals(0.2, discount, 0.01);
        //assertEquals(0.1, discount1, 0.01);
    }

    @Test
    public void testCalculateDiscountByGraduationYear() {
        StudentEntity student = new StudentEntity();
        //StudentEntity student1 = new StudentEntity();
        student.setGraduationYear(2020);
        //student1.setGraduationYear(2017);

        double discount = reportService.calculateDiscountByGraduationYear(student);
        //double discount1 = reportService.calculateDiscountByGraduationYear(student1);

        assertEquals(0.04, discount, 0.01);
        //assertEquals(0.0, discount1, 0.0);
    }

    @Test
    public void testCalculateDues() {
        StudentEntity student = new StudentEntity();
        student.setTypeSchool("Privado");

        int dues = reportService.calculateDues(student);

        assertEquals(4, dues);
    }

    /*
    @Test
    public void testCalculateDiscountByAverageScore() {
        ScoreEntity score = new ScoreEntity();
        score.setAverageExams(960);

        double discount = reportService.calculateDiscountByAverageScore(score);

        assertEquals(0.1, discount, 0.01);
    }

    @Test
    public void testCalculateAndSaveDiscount() {
        String rut = "12345678-9";

        TuitionEntity tuitionEntity = new TuitionEntity();
        tuitionEntity.setRut(rut);
        tuitionEntity.setPaymentType("Cuotas");

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setTypeSchool("Municipal");
        studentEntity.setGraduationYear(2020);
        studentEntity.setRut(rut);

        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setTotalExams(3);
        scoreEntity.setAverageExams(930);
        scoreEntity.setRut(rut);

        ReportEntity reportEntity = new ReportEntity();

        tuitionEntity.setStudentEntity(studentEntity);
        tuitionEntity.setScoreEntity(scoreEntity);
        reportEntity.setTuitionEntity(tuitionEntity);

        when(tuitionRepository.findByRut(rut)).thenReturn(tuitionEntity);

        reportService.calculateAndSaveDiscount(rut);

    }
    */
}

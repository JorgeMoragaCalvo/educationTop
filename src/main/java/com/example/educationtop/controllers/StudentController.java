package com.example.educationtop.controllers;

import com.example.educationtop.entities.StudentEntity;
import com.example.educationtop.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class StudentController {

    public final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students", studentService.allStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        StudentEntity studentEntity = new StudentEntity();
        model.addAttribute("student", studentEntity);
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") StudentEntity studentEntity){
        studentService.createStudents(studentEntity);
        return "redirect:/students";
    }
}
package com.example.educationtop.controllers;

import com.example.educationtop.entities.ScoreEntity;
import com.example.educationtop.entities.StudentEntity;
import com.example.educationtop.entities.TuitionEntity;
import com.example.educationtop.repositories.ScoreRepository;
import com.example.educationtop.repositories.StudentRepository;
import com.example.educationtop.repositories.TuitionRepository;
import com.example.educationtop.services.TuitionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TuitionController {

    private final TuitionService tuitionService;

    @Autowired
    public TuitionController(TuitionService tuitionService){
        this.tuitionService = tuitionService;
    }

    @GetMapping({"/registrations", "/"})
    public String getRegistrations(Model model){
        model.addAttribute("registrations", tuitionService.allRegistrations());
        return "registrations";
    }

    @GetMapping("/registrations/new")
    public String createRegistrationForm(Model model){
        TuitionEntity tuitionEntity = new TuitionEntity();
        model.addAttribute("tuition", tuitionEntity);
        return "create-tuition";
    }

    @PostMapping("/registrations")
    public String saveTuition(@ModelAttribute("tuition") TuitionEntity tuitionEntity){
        tuitionService.createRegistrations(tuitionEntity);
        return "redirect:/registrations";
    }

}

package com.example.educationtop.controllers;


import com.example.educationtop.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/your-route")
public class ScoreController {

    public final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/processing-excel")
    public String processingExcel(Model model) {
        try {
            scoreService.processingExcelFile();
            model.addAttribute("message", "Archivo procesado exitosamente.");
        } catch (IOException e) {
            model.addAttribute("message", "Error al procesar el archivo Excel.");
        }
        return "result";
    }

    @GetMapping("/show-scores")
    public String getScores(Model model){
        model.addAttribute("scores", scoreService.allScores());
        return "show-score";
    }
}
package com.example.educationtop.controllers;

import com.example.educationtop.entities.ReportEntity;
import com.example.educationtop.services.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @PostMapping("/calculate/{rut}")
    @ResponseBody
    public ResponseEntity<String> calculateDiscount(@PathVariable("rut") String rut){
        try{
            reportService.calculateAndSaveDiscount(rut);
            return ResponseEntity.ok("Saved Discount");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error " + e.getMessage());
        }
    }

    @GetMapping("/form")
    public String showReportForm() {
        return "report-form";
    }

    @PostMapping("/generate")
    public String generateReport(@RequestParam String rut, Model model) {
        reportService.calculateAndSaveDiscount(rut);
        ReportEntity report = reportService.getReportByRut(rut);
        model.addAttribute("report", report);
        return "report-result";
    }
}

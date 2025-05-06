package com.example.backend_service.controllers;

import com.example.backend_service.services.ExamJavaOopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExamJavaOopController {
    private final ExamJavaOopService examJavaOopService;

    public ExamJavaOopController(ExamJavaOopService examJavaOopService) {
        this.examJavaOopService = examJavaOopService;
    }

    @GetMapping("/check-exam-by-bankTestJavaOopID/{bankTestJavaOopID}")
    public ResponseEntity<Boolean> isExistExam_By_BankTestJavaOopID(@PathVariable Long bankTestJavaOopID) {
        return ResponseEntity.ok(examJavaOopService.isExistExam(bankTestJavaOopID));
    }
}

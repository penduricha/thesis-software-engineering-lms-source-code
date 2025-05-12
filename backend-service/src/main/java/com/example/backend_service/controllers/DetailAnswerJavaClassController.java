package com.example.backend_service.controllers;

import com.example.backend_service.models.DetailAnswerJavaClass;
import com.example.backend_service.services.DetailAnswerJavaClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api")
public class DetailAnswerJavaClassController {
    private final DetailAnswerJavaClassService detailAnswerJavaClassService;

    public DetailAnswerJavaClassController(DetailAnswerJavaClassService detailAnswerJavaClassService) {
        this.detailAnswerJavaClassService = detailAnswerJavaClassService;
    }

    @GetMapping("/detail-answers-java-class/get-list-detail-answers-java-class-by-detail-mark-student-id/{detailMarkStudentID}")
    public ResponseEntity<List<DetailAnswerJavaClass>>
    getListDetailAnswerJavaClass_By_DetailMarkStudentID(@PathVariable Long detailMarkStudentID) {
        return ResponseEntity.ok(detailAnswerJavaClassService.
                getListDetailAnswerJavaClass_By_DetailMarkStudentID(detailMarkStudentID));
    }


}

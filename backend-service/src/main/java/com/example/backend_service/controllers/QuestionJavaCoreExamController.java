package com.example.backend_service.controllers;

import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.QuestionJavaCoreExamRepository;
import com.example.backend_service.services.QuestionJavaCoreExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuestionJavaCoreExamController {
    private final QuestionJavaCoreExamRepository questionJavaCoreRepository;

    private final QuestionJavaCoreExamService questionJavaCoreExamService;

    public QuestionJavaCoreExamController(QuestionJavaCoreExamRepository questionJavaCoreExamRepository, QuestionJavaCoreExamService questionJavaCoreExamService) {
        this.questionJavaCoreRepository = questionJavaCoreExamRepository;
        this.questionJavaCoreExamService = questionJavaCoreExamService;
    }

//    @GetMapping("/get_total_question_by_exam_id/{examID}")
//    public ResponseEntity<Integer> getTotalQuestionsByExamID (@PathVariable Integer examID) {
//        return ResponseEntity.ok(questionJavaCoreRepository.getTotalQuestionsByExamID(Long.valueOf(examID)));
//    }

    @GetMapping("/get_questions_exam_exam_id/{examID}")
    public ResponseEntity<List<Map<String, Object>>>
    getTotalQuestionsByExamID (@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(questionJavaCoreExamService.getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(examID));
    }
}

package com.example.backend_service.controllers;

import com.example.backend_service.services.ResultQuestionJavaCoreService;
import org.springframework.data.repository.query.Param;
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
public class ResultJavaCoreController {
    private final ResultQuestionJavaCoreService resultQuestionJavaCoreService;

    public ResultJavaCoreController(ResultQuestionJavaCoreService resultQuestionJavaCoreService) {
        this.resultQuestionJavaCoreService = resultQuestionJavaCoreService;
    }

    @GetMapping("/result-java-core/get-list-test-case-answers/{resultQuestionJavaCoreID}")
    public ResponseEntity<List<Map<String, Object>>> getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID
            (@PathVariable Long resultQuestionJavaCoreID) throws HttpClientErrorException {
        return ResponseEntity.ok(resultQuestionJavaCoreService
                .getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID(resultQuestionJavaCoreID));
    }

    @GetMapping("/result-java-core/get-list-questions-exam-and-mark/{detailMarkStudentID}")
    public ResponseEntity<List<Map<String, Object>>> getListQuestionJavaCoreExamAndResult_By_Detail_MarkStudent_ID
            (@PathVariable Long detailMarkStudentID) throws HttpClientErrorException {
        return ResponseEntity.ok(resultQuestionJavaCoreService
                .getListQuestionJavaCoreExamAndResult_By_Detail_MarkStudent_ID(detailMarkStudentID));
    }
}

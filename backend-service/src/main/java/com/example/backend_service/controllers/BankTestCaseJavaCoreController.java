package com.example.backend_service.controllers;

import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.services.BankTestCaseJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BankTestCaseJavaCoreController {
    private final BankTestCaseJavaCoreService bankTestCaseJavaCoreService;

    @Autowired
    public BankTestCaseJavaCoreController(BankTestCaseJavaCoreService bankTestCaseJavaCoreService) {
        this.bankTestCaseJavaCoreService = bankTestCaseJavaCoreService;
    }

    @GetMapping("/bank_test_cases/get_bank_test_case_by_question_bank_id/{questionJavaCoreID}")
    public ResponseEntity<List<Map<String, Object>>> getRandom_10_Questions_JavaCore(@PathVariable Long questionJavaCoreID)
            throws HttpClientErrorException {
        List<BankTestCaseJavaCore> bankTestCaseJavaCoreList =
                bankTestCaseJavaCoreService.getTestCases_By_QuestionJavaCoreID(questionJavaCoreID);
        // Trích xuất các trường và tạo danh sách kết quả
        List<Map<String, Object>> responseList = bankTestCaseJavaCoreList.stream()
                .map(testCase -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("bankTestCaseID", testCase.getBankTestCaseID());
                    map.put("inputTest", testCase.getInputTest());
                    map.put("outputExpect", testCase.getOutputExpect());
                    map.put("note", testCase.getNote());
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/bank_test_cases/get_test_case_by_question_java_core_exam_id/{questionJavaCoreExamID}")
    public ResponseEntity<List<Map<String, Object>>> getTestCases_By_QuestionJavaCoreExamID(@PathVariable Long questionJavaCoreExamID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(
                bankTestCaseJavaCoreService.getTestCases_By_QuestionJavaCoreExamID(questionJavaCoreExamID));
    }
}

package com.example.backend_service.controllers;

import com.example.backend_service.services.BankQuestionJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BankQuestionJavaCoreController {
    private final BankQuestionJavaCoreService bankQuestionJavaCoreService;

    @Autowired
    public BankQuestionJavaCoreController(BankQuestionJavaCoreService bankQuestionJavaCoreService) {
        this.bankQuestionJavaCoreService = bankQuestionJavaCoreService;
    }

    @GetMapping("/questions/random_10_question_from_bank")
    public ResponseEntity<List<Map<String, Object>>> getRandom_10_Questions_JavaCore() throws HttpClientErrorException {
        List<Map<String, Object>> listRandom = bankQuestionJavaCoreService.getRandom_10_Questions_JavaCore();
//        // Chuyển đổi danh sách
//        List<Map<String, Object>> transformedList = originalList.stream()
//                .map(originalMap -> {
//                    Map<String, Object> newMap = new HashMap<>();
//                    newMap.put("codeSample", originalMap.get("code_sample"));
//                    newMap.put("contentQuestion", originalMap.get("content_question"));
//                    newMap.put("questionJavaCoreID", originalMap.get("question_java_core_id"));
//                    return newMap;
//                })
//                .collect(Collectors.toList());
        return ResponseEntity.ok(listRandom);
    }
}

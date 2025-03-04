package com.example.backend_service.controllers;

import com.example.backend_service.transactions_service.DebugJavaCoreSubmit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubmitCodeController {
    private final DebugJavaCoreSubmit debugJavaCoreSubmit;

    public SubmitCodeController(DebugJavaCoreSubmit debugJavaCoreSubmit) {
        this.debugJavaCoreSubmit = debugJavaCoreSubmit;
    }

//    @PostMapping("/submit-code/submit-data-exam-java-core")
//    public ResponseEntity<Map<String, Object>> postSubmitDataExamJavaCore(Map<String, Object> dataSubmitPost)
//            throws HttpClientErrorException {
//        String studentID = (String) dataSubmitPost.get("studentID");
//        Integer examIDInt = (Integer) dataSubmitPost.get("examID");
//        Long examID = examIDInt != null ? examIDInt.longValue() : null;
//        List<Map<String, Object>> answerQuestions = (List<Map<String, Object>>) dataSubmitPost.get("answerQuestions");
//        return
//    }
}

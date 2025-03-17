package com.example.backend_service.controllers.test_service;

import com.example.backend_service.dto.CodeSubmitAndCodeMain;
import com.example.backend_service.services.MarkStudentService;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResultQuestionJavaCoreControllerTest {
    private final MarkStudentService markStudentService;

    public ResultQuestionJavaCoreControllerTest(MarkStudentService markStudentService) {
        this.markStudentService = markStudentService;
    }

    @GetMapping("/detail-mark-student/get-code-submit-and-code-main-by-detail-mark-student-id/{detailMarkStudentID}")
    public List<CodeSubmitAndCodeMain>
    getCodeSubmittedAndCodeRunToOutput(@PathVariable Long detailMarkStudentID) throws HttpClientErrorException {
        return markStudentService.get_Code_Submitted_And_Code_Run_To_Output(detailMarkStudentID);
    }
}

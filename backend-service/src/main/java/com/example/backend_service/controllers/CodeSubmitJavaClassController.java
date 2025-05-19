package com.example.backend_service.controllers;

import com.example.backend_service.models.CodeSubmitJavaClass;

import com.example.backend_service.services.CodeSubmitJavaClassService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class CodeSubmitJavaClassController {
    private final CodeSubmitJavaClassService codeSubmitJavaClassService;

    public CodeSubmitJavaClassController(CodeSubmitJavaClassService codeSubmitJavaClassService) {
        this.codeSubmitJavaClassService = codeSubmitJavaClassService;
    }

    @GetMapping("/code-submit-java-class/get-code-submit-java-class-by-detail-mark-student-id/{detailMarkStudentID}")
    public ResponseEntity<CodeSubmitJavaClass>
    getCodeSubmitJavaClass_By_DetailMarkStudentID(@PathVariable Long detailMarkStudentID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(codeSubmitJavaClassService
                .getCodeSubmitJavaClass_By_DetailMarkStudentID(detailMarkStudentID));
    }
}

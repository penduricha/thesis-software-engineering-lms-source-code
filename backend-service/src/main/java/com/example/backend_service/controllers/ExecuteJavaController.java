package com.example.backend_service.controllers;

import com.example.backend_service.debugs.ExecuteJavaCoreByStudentID;
import com.example.backend_service.services.ExecuteJavaService;
import org.apache.groovy.util.JavaShellCompilationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ExecuteJavaController {
    private final ExecuteJavaService executeJavaService;

    public ExecuteJavaController(ExecuteJavaService executeJavaService) {
        this.executeJavaService = executeJavaService;
    }

    @PostMapping("/debug-code-testing-java-core/{studentID}/{examID}/{questionJavaCoreExamID}")
    public ResponseEntity<String> getJavaOutput_By_StudentID(@RequestBody Map<String, Object> mapCode,
                                                            @PathVariable String studentID,
                                                            @PathVariable Long examID,
                                                            @PathVariable Long questionJavaCoreExamID)
            throws HttpClientErrorException {
        String code = (String) mapCode.get("codeMain");
        return ResponseEntity.ok(executeJavaService.
                getJavaOutput_By_StudentID(code, studentID, examID, questionJavaCoreExamID));
    }
}

package com.example.backend_service.services.i_service;


import com.example.backend_service.models.BankTestCaseJavaCore;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface I_BankTestCaseJavaCoreService {
    public List<BankTestCaseJavaCore> getTestCases_By_QuestionJavaCoreID(Long questionJavaCoreID);

    public List<Map<String, Object>> getTestCases_By_QuestionJavaCoreExamID(Long questionJavaCoreExamID);
}

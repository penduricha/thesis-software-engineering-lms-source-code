package com.example.backend_service.services.i_service;


import com.example.backend_service.models.BankQuestionJavaCore;

import java.util.List;
import java.util.Map;

public interface I_BankQuestionJavaCoreService {
    public List<Map<String, Object>> getRandom_10_Questions_JavaCore();

    public List<BankQuestionJavaCore> getBankQuestions_JavaCore();
}

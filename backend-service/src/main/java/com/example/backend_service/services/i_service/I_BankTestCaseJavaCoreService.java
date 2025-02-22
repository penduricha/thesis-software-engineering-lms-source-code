package com.example.backend_service.services.i_service;


import com.example.backend_service.models.BankTestCaseJavaCore;

import java.util.List;

public interface I_BankTestCaseJavaCoreService {
    public List<BankTestCaseJavaCore> getTestCases_By_QuestionJavaCoreID(Long questionJavaCoreID);

}

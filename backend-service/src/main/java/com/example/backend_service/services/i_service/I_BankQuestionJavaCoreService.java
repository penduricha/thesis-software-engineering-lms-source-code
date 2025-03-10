package com.example.backend_service.services.i_service;


import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface I_BankQuestionJavaCoreService {
    public List<Map<String, Object>> getRandom_10_Questions_JavaCore();

    public List<BankQuestionJavaCore> getBankQuestions_JavaCore();

    public BankQuestionJavaCore createBankQuestionJavaCore(BankQuestionJavaCore bankQuestionJavaCore,
                                                           List<BankTestCaseJavaCore> bankTestCaseJavaCoreList);
    public Long deleteBankQuestionJavaCore(Long questionJavaCoreID);

    public BankQuestionJavaCore updateBankQuestionJavaCore(BankQuestionJavaCore bankQuestionJavaCore,
                                                           List<BankTestCaseJavaCore> bankTestCaseJavaCoreList);

    public Long getTotalBankQuestionJavaCore();

    List<Map<String, Object>> getRandom_Questions_JavaCore_By_NumberQuestions
            (int numberOfQuestions);
}

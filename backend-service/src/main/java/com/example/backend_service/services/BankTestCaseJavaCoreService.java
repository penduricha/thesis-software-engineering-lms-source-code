package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.repositories.BankQuestionJavaCoreRepository;
import com.example.backend_service.repositories.BankTestCaseJavaCoreRepository;
import com.example.backend_service.services.i_service.I_BankTestCaseJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankTestCaseJavaCoreService implements I_BankTestCaseJavaCoreService {

    private final BankTestCaseJavaCoreRepository bankTestCaseJavaCoreRepository;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    @Autowired
    public BankTestCaseJavaCoreService(BankTestCaseJavaCoreRepository bankTestCaseJavaCoreRepository, BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository) {
        this.bankTestCaseJavaCoreRepository = bankTestCaseJavaCoreRepository;
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
    }

    @Override
    public List<BankTestCaseJavaCore> getTestCases_By_QuestionJavaCoreID(Long questionJavaCoreID) throws JpaSystemException {
        BankQuestionJavaCore bankQuestionJavaCore =
                bankQuestionJavaCoreRepository.findBankQuestionJavaCoreByQuestionJavaCoreID(questionJavaCoreID);
        if(bankQuestionJavaCore != null) {
//            System.out.println("List test cases: "+bankTestCaseJavaCoreRepository
//                    .getBankTestCaseJavaCoresByBankQuestionJavaCore_QuestionJavaCoreID(questionJavaCoreID));
            return bankTestCaseJavaCoreRepository
                    .getBankTestCaseJavaCoresByBankQuestionJavaCore_QuestionJavaCoreID(questionJavaCoreID);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getTestCases_By_QuestionJavaCoreExamID(Long questionJavaCoreExamID) throws JpaSystemException {
        return bankTestCaseJavaCoreRepository.getTestCases_By_QuestionJavaCoreExamID(questionJavaCoreExamID).stream()
                .map(originalMap -> {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("inputTest", originalMap.get("input_test"));
                    newMap.put("outputExpect", originalMap.get("output_expect"));
                    newMap.put("note", originalMap.get("note"));
                    return newMap;
                })
                .collect(Collectors.toList());
    }
}

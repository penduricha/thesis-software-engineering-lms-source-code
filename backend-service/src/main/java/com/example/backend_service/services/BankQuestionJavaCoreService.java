package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.repositories.BankQuestionJavaCoreRepository;
import com.example.backend_service.services.i_service.I_BankQuestionJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankQuestionJavaCoreService implements I_BankQuestionJavaCoreService {

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    @Autowired
    public BankQuestionJavaCoreService(BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository) {
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
    }

    @Override
    public List<Map<String, Object>> getRandom_10_Questions_JavaCore() {
        if(getBankQuestions_JavaCore().size() < 10) {
            return null;
        } else {
            return bankQuestionJavaCoreRepository.getRandom_10_Questions_JavaCore().stream()
                    .map(originalMap -> {
                        Map<String, Object> newMap = new HashMap<>();
                        newMap.put("codeSample", originalMap.get("code_sample"));
                        newMap.put("contentQuestion", originalMap.get("content_question"));
                        newMap.put("questionJavaCoreID", originalMap.get("question_java_core_id"));
                        return newMap;
                    })
                    .collect(Collectors.toList());
        }
    }

    //lấy hết danh sách để ktra
    @Override
    public List<BankQuestionJavaCore> getBankQuestions_JavaCore() {
        return bankQuestionJavaCoreRepository.findAll();
    }
}

package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.repositories.BankQuestionJavaCoreRepository;
import com.example.backend_service.repositories.BankTestCaseJavaCoreRepository;
import com.example.backend_service.services.i_service.I_BankTestCaseJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}

package com.example.backend_service.repositories;


import com.example.backend_service.models.BankTestCaseJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTestCaseJavaCoreRepository extends JpaRepository<BankTestCaseJavaCore,Long> {

    List<BankTestCaseJavaCore> getBankTestCaseJavaCoresByBankQuestionJavaCore_QuestionJavaCoreID(Long questionJavaCoreID);
}

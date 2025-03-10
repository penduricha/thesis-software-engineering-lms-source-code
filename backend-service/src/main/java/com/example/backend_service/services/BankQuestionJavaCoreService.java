package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.repositories.BankQuestionJavaCoreRepository;
import com.example.backend_service.repositories.BankTestCaseJavaCoreRepository;
import com.example.backend_service.services.i_service.I_BankQuestionJavaCoreService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BankQuestionJavaCoreService implements I_BankQuestionJavaCoreService {
    @PersistenceContext
    private EntityManager entityManager;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    private final BankTestCaseJavaCoreRepository bankTestCaseJavaCoreRepository;

    @Autowired
    public BankQuestionJavaCoreService(BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository, BankTestCaseJavaCoreRepository bankTestCaseJavaCoreRepository) {
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
        this.bankTestCaseJavaCoreRepository = bankTestCaseJavaCoreRepository;
    }

    @Override
    public List<Map<String, Object>> getRandom_10_Questions_JavaCore() throws JpaSystemException {
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
    public List<BankQuestionJavaCore> getBankQuestions_JavaCore() throws JpaSystemException {
        return bankQuestionJavaCoreRepository.findAll(Sort.by(Sort.Direction.ASC,
                "questionJavaCoreID"));
    }

    @Override
    public BankQuestionJavaCore createBankQuestionJavaCore(
            BankQuestionJavaCore bankQuestionJavaCore,
            List<BankTestCaseJavaCore> bankTestCaseJavaCoreList){
        if (!bankTestCaseJavaCoreList.isEmpty()) {
            for (BankTestCaseJavaCore testCase : bankTestCaseJavaCoreList) {
                //System.out.println(testCase);
                bankQuestionJavaCore.getBankTestCaseJavaCores().add(testCase);
                testCase.setBankQuestionJavaCore(bankQuestionJavaCore);
            }
            return bankQuestionJavaCoreRepository.save(bankQuestionJavaCore);
        }
        return null;
    }

    @Override
    @Transactional
    public Long deleteBankQuestionJavaCore(Long questionJavaCoreID) throws JpaSystemException {
        BankQuestionJavaCore bankQuestionJavaCore = bankQuestionJavaCoreRepository
                .findBankQuestionJavaCoreByQuestionJavaCoreID(questionJavaCoreID);


        if(bankQuestionJavaCore !=null) {
            String sqlDeleteTestCases =
                    "delete from bank_test_case_java_core where question_java_core_id = :questionJavaCoreID";

            String sqlDeleteBankQuestionJavaCore =
                    "delete from bank_question_java_core where question_java_core_id = :questionJavaCoreID";

            String sqlDeleteQuestionJavaCoreExam =
                    "delete from question_java_core_exam where question_java_core_id = :questionJavaCoreID";


            entityManager.createNativeQuery(sqlDeleteTestCases)
                    .setParameter("questionJavaCoreID",
                            bankQuestionJavaCore.getQuestionJavaCoreID())
                    .executeUpdate();

            entityManager.createNativeQuery(sqlDeleteQuestionJavaCoreExam)
                    .setParameter("questionJavaCoreID",
                            bankQuestionJavaCore.getQuestionJavaCoreID())
                    .executeUpdate();

            entityManager.createNativeQuery(sqlDeleteBankQuestionJavaCore)
                    .setParameter("questionJavaCoreID",
                            bankQuestionJavaCore.getQuestionJavaCoreID())
                    .executeUpdate();
            return bankQuestionJavaCore.getQuestionJavaCoreID();
        }
        return null;
    }

    @Override
    public BankQuestionJavaCore updateBankQuestionJavaCore(
            BankQuestionJavaCore bankQuestionJavaCore,
            List<BankTestCaseJavaCore> bankTestCaseJavaCoreList) throws JpaSystemException {
        BankQuestionJavaCore bankQuestionJavaCoreFound = bankQuestionJavaCoreRepository
                .findBankQuestionJavaCoreByQuestionJavaCoreID(bankQuestionJavaCore.getQuestionJavaCoreID());
        if (bankQuestionJavaCoreFound != null) {
            bankQuestionJavaCoreFound.setContentQuestion(bankQuestionJavaCore.getContentQuestion());
            bankQuestionJavaCoreFound.setCodeSample(bankQuestionJavaCore.getCodeSample());
            bankQuestionJavaCoreFound.setCodeRunToOutput(bankQuestionJavaCore.getCodeRunToOutput());
            List<BankTestCaseJavaCore> existingTestCases = bankTestCaseJavaCoreRepository
                    .getBankTestCaseJavaCoresByBankQuestionJavaCore_QuestionJavaCoreID
                            (bankQuestionJavaCore.getQuestionJavaCoreID());
            for (BankTestCaseJavaCore newTestCase : bankTestCaseJavaCoreList) {
                boolean found = false;
                for (BankTestCaseJavaCore existingTestCase : existingTestCases) {
                    if (existingTestCase.getInputTest().equals(newTestCase.getInputTest())) {
                        // Update the existing test case
                        existingTestCase.setOutputExpect(newTestCase.getOutputExpect());
                        existingTestCase.setNote(newTestCase.getNote());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    newTestCase.setBankQuestionJavaCore(bankQuestionJavaCoreFound);
                    bankTestCaseJavaCoreRepository.save(newTestCase);
                }
            }
            // Save the updated question
            return bankQuestionJavaCoreRepository.save(bankQuestionJavaCoreFound);
        }
        throw new EntityNotFoundException("BankQuestionJavaCore not found with ID: "
                + bankQuestionJavaCore.getQuestionJavaCoreID());
    }

    @Override
    public Long getTotalBankQuestionJavaCore() throws JpaSystemException {
        return bankQuestionJavaCoreRepository.getTotalBankQuestionJavaCore();
    }

    @Override
    public List<Map<String, Object>> getRandom_Questions_JavaCore_By_NumberQuestions(int numberOfQuestions)
            throws JpaSystemException {
        if(getBankQuestions_JavaCore().size() <  numberOfQuestions) {
            return null;
        } else {
            return
                    bankQuestionJavaCoreRepository
                            .getRandom_Questions_JavaCore_By_NumberQuestions(numberOfQuestions).stream()
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
}

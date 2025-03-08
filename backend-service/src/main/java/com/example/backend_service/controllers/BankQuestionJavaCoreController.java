package com.example.backend_service.controllers;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.services.BankQuestionJavaCoreService;
import com.example.backend_service.services.BankTestCaseJavaCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BankQuestionJavaCoreController {
    private final BankQuestionJavaCoreService bankQuestionJavaCoreService;

    private final BankTestCaseJavaCoreService bankTestCaseJavaCoreService;

    @Autowired
    public BankQuestionJavaCoreController(BankQuestionJavaCoreService bankQuestionJavaCoreService, BankTestCaseJavaCoreService bankTestCaseJavaCoreService) {
        this.bankQuestionJavaCoreService = bankQuestionJavaCoreService;
        this.bankTestCaseJavaCoreService = bankTestCaseJavaCoreService;
    }

    @GetMapping("/questions/random_10_question_from_bank")
    public ResponseEntity<List<Map<String, Object>>>
    getRandom_10_Questions_JavaCore() throws HttpClientErrorException {
        List<Map<String, Object>> listRandom = bankQuestionJavaCoreService.getRandom_10_Questions_JavaCore();
        return ResponseEntity.ok(listRandom);
    }

    @GetMapping("/bank-question-java-core/get-questions-java-core")
    public ResponseEntity<List<BankQuestionJavaCore>> getBankQuestionJavaCore()
            throws HttpClientErrorException{
        return ResponseEntity.ok(bankQuestionJavaCoreService.getBankQuestions_JavaCore());
    }

    @DeleteMapping
            ("/bank-question-java-core/delete-create-question-and-test-cases-by-question-java-core-id/{questionJavaCoreID}")
    public ResponseEntity<Long> deleteBankQuestionJavaCore(@PathVariable Long questionJavaCoreID)
            throws HttpClientErrorException{
        return ResponseEntity
                .ok(bankQuestionJavaCoreService.deleteBankQuestionJavaCore(questionJavaCoreID));
    }

    @PostMapping("/bank-question-java-core/create-question-and-test-cases")
    public ResponseEntity<BankQuestionJavaCore> postBankQuestionJavaCore(@RequestBody Map<String, Object> inputPost)
            throws HttpClientErrorException{
        // Kiểm tra và lấy các trường từ inputPost
        String contentQuestion = (String) inputPost.get("contentQuestion");
        String codeSample = (String) inputPost.get("codeSample");
        String codeRunToOutput = (String) inputPost.get("codeRunToOutput");
        // Kiểm tra và lấy danh sách test case
        List<Map<String, Object>> bankTestCaseJavaCoreList =
                (List<Map<String, Object>> ) inputPost.get("bankTestCaseJavaCoreList");
        //doi thanh list object
        List<BankTestCaseJavaCore> convertedBankTestCaseJavaCoreList = new ArrayList<>();
        for (Map<String, Object> map : bankTestCaseJavaCoreList) {
            String inputTest = (String) map.get("inputTest");
            String outputTest = (String) map.get("outputExpect");
            String note = (String) map.get("note");
            // Create a new BankTestCaseJavaCore object and add it to the list
            BankTestCaseJavaCore testCase = new BankTestCaseJavaCore();
            testCase.setInputTest(inputTest);
            testCase.setOutputExpect(outputTest);
            testCase.setNote(note);
            convertedBankTestCaseJavaCoreList.add(testCase);
        }

        // Tạo đối tượng BankQuestionJavaCore
        BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
        bankQuestionJavaCore.setContentQuestion(contentQuestion);
        bankQuestionJavaCore.setCodeSample(codeSample);
        bankQuestionJavaCore.setCodeRunToOutput(codeRunToOutput);
        // Kiểm tra xem danh sách test case có hợp lệ không
        return ResponseEntity.ok(
                bankQuestionJavaCoreService.createBankQuestionJavaCore(bankQuestionJavaCore,
                        convertedBankTestCaseJavaCoreList));
    }

    @PutMapping("/bank-question-java-core/update-question-and-test-cases")
    public ResponseEntity<BankQuestionJavaCore> putBankQuestionJavaCore(@RequestBody Map<String, Object> inputPut)
            throws HttpClientErrorException{
        // Kiểm tra và lấy các trường từ inputPost
        //Get map kieu Long
        Integer questionJavaCoreIDInteger = (Integer) inputPut.get("questionJavaCoreID");
        Long questionJavaCoreID = questionJavaCoreIDInteger != null ? questionJavaCoreIDInteger.longValue() : null;
        String contentQuestion = (String) inputPut.get("contentQuestion");
        String codeSample = (String) inputPut.get("codeSample");
        String codeRunToOutput = (String) inputPut.get("codeRunToOutput");
        // Kiểm tra và lấy danh sách test case
        List<Map<String, Object>> bankTestCaseJavaCoreList =
                (List<Map<String, Object>> ) inputPut.get("bankTestCaseJavaCoreList");
        //doi thanh list object
        List<BankTestCaseJavaCore> convertedBankTestCaseJavaCoreList = new ArrayList<>();
        for (Map<String, Object> map : bankTestCaseJavaCoreList) {
            String inputTest = (String) map.get("inputTest");
            String outputTest = (String) map.get("outputExpect");
            String note = (String) map.get("note");
            // Create a new BankTestCaseJavaCore object and add it to the list
            BankTestCaseJavaCore testCase = new BankTestCaseJavaCore();
            testCase.setInputTest(inputTest);
            testCase.setOutputExpect(outputTest);
            testCase.setNote(note);
            convertedBankTestCaseJavaCoreList.add(testCase);
        }

        // Tạo đối tượng BankQuestionJavaCore
        BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
        bankQuestionJavaCore.setQuestionJavaCoreID(questionJavaCoreID);
        bankQuestionJavaCore.setContentQuestion(contentQuestion);
        bankQuestionJavaCore.setCodeSample(codeSample);
        bankQuestionJavaCore.setCodeRunToOutput(codeRunToOutput);
        // Kiểm tra xem danh sách test case có hợp lệ không
        return ResponseEntity.ok(
                bankQuestionJavaCoreService.updateBankQuestionJavaCore
                        (bankQuestionJavaCore, convertedBankTestCaseJavaCoreList));
    }

    @GetMapping("/bank-question-java-core/get-test-cases-by-question-java-core-id/{questionJavaCoreID}")
    public ResponseEntity<List<BankTestCaseJavaCore>>
    getTestCases_By_QuestionJavaCoreID(@PathVariable Long questionJavaCoreID)
            throws HttpClientErrorException{
        return ResponseEntity.ok(bankTestCaseJavaCoreService.getTestCases_By_QuestionJavaCoreID(questionJavaCoreID));
    }
}

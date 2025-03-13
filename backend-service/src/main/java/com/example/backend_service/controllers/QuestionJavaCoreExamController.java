package com.example.backend_service.controllers;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.QuestionJavaCoreExamRepository;
import com.example.backend_service.services.QuestionJavaCoreExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuestionJavaCoreExamController {
    private final QuestionJavaCoreExamRepository questionJavaCoreRepository;

    private final QuestionJavaCoreExamService questionJavaCoreExamService;

    public QuestionJavaCoreExamController(QuestionJavaCoreExamRepository questionJavaCoreExamRepository, QuestionJavaCoreExamService questionJavaCoreExamService) {
        this.questionJavaCoreRepository = questionJavaCoreExamRepository;
        this.questionJavaCoreExamService = questionJavaCoreExamService;
    }

//    @GetMapping("/get_total_question_by_exam_id/{examID}")
//    public ResponseEntity<Integer> getTotalQuestionsByExamID (@PathVariable Integer examID) {
//        return ResponseEntity.ok(questionJavaCoreRepository.getTotalQuestionsByExamID(Long.valueOf(examID)));
//    }

    @GetMapping("/get_questions_java_core_exam_by_exam_id/{examID}")
    public ResponseEntity<List<Map<String, Object>>>
    getTotalQuestionsByExamID (@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(questionJavaCoreExamService.getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(examID));
    }

    @GetMapping("/switch-marked-flag-by-question-java-core-exam-id/{questionJavaCoreExamID}")
    public ResponseEntity<QuestionJavaCoreExam>
        get_Switch_Marked_Flag_By_QuestionJavaCoreExam_ID(@PathVariable Long questionJavaCoreExamID)
        throws HttpClientErrorException {
        return ResponseEntity.ok(questionJavaCoreExamService.
                update_Switch_Marked_Flag_By_QuestionJavaCoreExam_ID(questionJavaCoreExamID));
    }

    @GetMapping("/switch-marked-flag-false-by-exam-id/{examID}")
    public ResponseEntity<Exam>
    get_Switch_Marked_Flag_False_By_Exam_ID(@PathVariable Long examID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(questionJavaCoreExamService.update_Marked_Flag_False_By_ExamID(examID));
    }

    @PutMapping("/put_all_questions_java_core_exam_by_exam_id/{examID}")
    public ResponseEntity<Exam> putQuestionJavaCoreExams_By_ExamID
            (@RequestBody List<Map<String, Object>> dataPut, @PathVariable Long examID)
            throws HttpClientErrorException {
        List<QuestionJavaCoreExam> convertedListQuestionJavaCoreExam = new ArrayList<>();
        for(Map<String, Object> data: dataPut) {
            String codeSample = (String) data.get("codeSample");
            String contentQuestion = (String) data.get("contentQuestion");
            Integer questionJavaCoreIDInteger = (Integer) data.get("questionJavaCoreID");
            Long questionJavaCoreID = questionJavaCoreIDInteger != null ? questionJavaCoreIDInteger.longValue() : null;
            //covert to double
            Object scoreObject = data.get("score");
            double score = 0.0;
            if (scoreObject instanceof Integer) {
                score = (Integer) scoreObject;
            } else if (scoreObject instanceof Double) {
                score = (Double) scoreObject;
            } else if (scoreObject instanceof String) {
                try {
                    score = Double.parseDouble((String) scoreObject);
                } catch (NumberFormatException e) {
                    // Xử lý lỗi nếu không thể chuyển đổi thành số
                    System.err.println("Invalid score format: " + scoreObject);
                }
            }

            BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
            bankQuestionJavaCore.setQuestionJavaCoreID(questionJavaCoreID);

            QuestionJavaCoreExam questionJavaCoreExam = new QuestionJavaCoreExam();
            questionJavaCoreExam.setCodeSample(codeSample);
            questionJavaCoreExam.setContentQuestion(contentQuestion);
            questionJavaCoreExam.setScore(score);

            //relationship
            bankQuestionJavaCore.getQuestionJavaCoreExams().add(questionJavaCoreExam);
            questionJavaCoreExam.setBankQuestionJavaCore(bankQuestionJavaCore);

            convertedListQuestionJavaCoreExam.add(questionJavaCoreExam);
        }

        return ResponseEntity.ok(questionJavaCoreExamService.updateQuestionJavaCoreExams_By_ExamID(examID, convertedListQuestionJavaCoreExam));
    }
}

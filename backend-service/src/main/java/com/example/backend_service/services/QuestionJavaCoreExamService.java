package com.example.backend_service.services;

import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.QuestionJavaCoreExamRepository;
import com.example.backend_service.services.i_service.I_QuestionJavaCoreExamService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QuestionJavaCoreExamService implements I_QuestionJavaCoreExamService {
    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    public QuestionJavaCoreExamService(QuestionJavaCoreExamRepository questionJavaCoreExamRepository) {
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
    }


    @Override
    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams_By_ExamID(Long examID) {
        // Retrieve the list from the repository
        List<QuestionJavaCoreExam> questions = questionJavaCoreExamRepository.findQuestionJavaCoreExamsByExam_ExamID(examID);
        // Shuffle the list
        if (questions != null) {
            Collections.shuffle(questions);
            return questions;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(Long examID) {
        List<Map<String, Object>> questionsQuery = questionJavaCoreExamRepository.findQuestionJavaCoreExamsByExam_ExamID_Map(examID);
        if(questionsQuery != null) {
            Collections.shuffle(questionsQuery);
            return questionsQuery;
        }
        return null;
    }


}

package com.example.backend_service.services.i_service;

import com.example.backend_service.models.QuestionJavaCoreExam;

import java.util.*;

public interface I_QuestionJavaCoreExamService {
    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams_By_ExamID(Long examID);

    public List<Map<String, Object>> getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(Long examID);
}

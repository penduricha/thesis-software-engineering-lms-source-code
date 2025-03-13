package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;

import java.util.*;

public interface I_QuestionJavaCoreExamService {
    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams_By_ExamID(Long examID);

    public List<Map<String, Object>> getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(Long examID);

    public Exam updateQuestionJavaCoreExams_By_ExamID (Long examID, List<QuestionJavaCoreExam> questionJavaCoreExams);

    public QuestionJavaCoreExam update_Switch_Marked_Flag_By_QuestionJavaCoreExam_ID(Long questionJavaCoreExamID);
}

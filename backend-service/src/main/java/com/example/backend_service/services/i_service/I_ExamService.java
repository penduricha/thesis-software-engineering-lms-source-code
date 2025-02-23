package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Exam;

import java.util.List;
import java.util.Map;

public interface I_ExamService {
    public Exam createExam(Exam exam, Long courseID);

    public List<Map<String, Object>> getExamsByCourseID(Long courseID);

    public Map<String, Object> viewExam_By_ExamID(Long examID, Long courseID);

    public Long deleteExam_By_ExamID(Long examID);
}

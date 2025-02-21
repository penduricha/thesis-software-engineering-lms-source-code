package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Exam;

public interface I_ExamService {
    public Exam createExam(Exam exam, Long courseID);
}

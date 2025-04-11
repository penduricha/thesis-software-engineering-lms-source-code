package com.example.backend_service.services.i_service;

import com.example.backend_service.models.ExamJavaOop;

import java.util.List;

public interface I_ExamOopJavaService {
    public List<ExamJavaOop> getAllExamJavaOop();

    public ExamJavaOop getExamJavaOopBy_BankTestJavaOopID(Long bankTestJavaOopID);
}

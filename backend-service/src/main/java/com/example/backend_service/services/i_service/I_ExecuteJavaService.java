package com.example.backend_service.services.i_service;

public interface I_ExecuteJavaService {
    public String getJavaOutput_By_StudentID(String code, String studentID, Long examID, Long questionJavaCoreExamID);
}

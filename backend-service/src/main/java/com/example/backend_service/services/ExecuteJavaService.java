package com.example.backend_service.services;

import com.example.backend_service.debugs.ExecuteJavaCoreByStudentID;
import com.example.backend_service.services.i_service.I_ExecuteJavaService;
import org.apache.groovy.util.JavaShellCompilationException;
import org.springframework.stereotype.Service;

@Service
public class ExecuteJavaService implements I_ExecuteJavaService {

    @Override
    public String getJavaOutput_By_StudentID(String code, String studentID, Long examID, Long questionJavaCoreExamID)
        throws JavaShellCompilationException {
        ExecuteJavaCoreByStudentID
                executeJavaCoreByStudentID = new ExecuteJavaCoreByStudentID(studentID, examID, questionJavaCoreExamID);
        return executeJavaCoreByStudentID.executeJava(code);
    }
}

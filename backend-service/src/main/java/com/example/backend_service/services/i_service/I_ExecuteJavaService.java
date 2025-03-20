package com.example.backend_service.services.i_service;

import java.util.Map;

public interface I_ExecuteJavaService {
    public String getJavaOutput_By_StudentID(String code, String studentID, Long examID, Long questionJavaCoreExamID);

    public Boolean testCompiler_MainCode_By_LectureID(Map<String, Object> mapCodeMainLecture);
}

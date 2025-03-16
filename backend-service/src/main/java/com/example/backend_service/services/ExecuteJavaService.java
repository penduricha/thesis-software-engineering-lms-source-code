package com.example.backend_service.services;

import com.example.backend_service.debugs.CompileJavaCodeByLectureID;
import com.example.backend_service.debugs.ExecuteJavaCoreByStudentID;
import com.example.backend_service.models.Lecture;
import com.example.backend_service.repositories.LectureRepository;
import com.example.backend_service.services.i_service.I_ExecuteJavaService;
import org.apache.groovy.util.JavaShellCompilationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExecuteJavaService implements I_ExecuteJavaService {

    private final LectureRepository lectureRepository;

    public ExecuteJavaService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public String getJavaOutput_By_StudentID(String code, String studentID, Long examID, Long questionJavaCoreExamID)
        throws JavaShellCompilationException {
        ExecuteJavaCoreByStudentID
                executeJavaCoreByStudentID = new ExecuteJavaCoreByStudentID(studentID, examID, questionJavaCoreExamID);
        return executeJavaCoreByStudentID.executeJava(code);
    }

    public String covertLectureID(String lectureID) {
        if(lectureID.charAt(0) == '0'){
            return lectureID.substring(1);
        }
        return null;
    }

    @Override
    public Boolean testCompiler_MainCode_By_LectureID(Map<String, Object> mapCodeMainLecture) {
        String lectureID = (String) mapCodeMainLecture.get("lectureID");
        String code = (String) mapCodeMainLecture.get("code");
        Lecture lectureFound = lectureRepository.findLectureByLectureID(covertLectureID(lectureID));
        if(lectureFound != null) {
            CompileJavaCodeByLectureID compileJavaCodeByLectureID = new
                    CompileJavaCodeByLectureID(covertLectureID(lectureID));
            return compileJavaCodeByLectureID.compileJavaCodeByLectureID(code);
        }
        return false;
    }
}

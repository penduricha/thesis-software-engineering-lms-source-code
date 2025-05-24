package com.example.backend_service.services;

import com.example.backend_service.controllers.LecturerIDFunc;
import com.example.backend_service.debugs.CompileJavaCodeByLectureID;
import com.example.backend_service.debugs.ExecuteJavaCoreByStudentID;

import com.example.backend_service.models.Lecturer;
import com.example.backend_service.repositories.LecturerRepository;
import com.example.backend_service.services.i_service.I_ExecuteJavaService;
import org.apache.groovy.util.JavaShellCompilationException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExecuteJavaService implements I_ExecuteJavaService {

    private final LecturerRepository lecturerRepository;

    public ExecuteJavaService(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public String getJavaOutput_By_StudentID(String code, String studentID, Long examID, Long questionJavaCoreExamID)
        throws JavaShellCompilationException {
        ExecuteJavaCoreByStudentID
                executeJavaCoreByStudentID = new ExecuteJavaCoreByStudentID(studentID, examID, questionJavaCoreExamID);
        return executeJavaCoreByStudentID.executeJava(code);
    }

    public String covertLectureID(String lecturerID) {
        return LecturerIDFunc.covertLectureID(lecturerID);
    }

    @Override
    public Boolean testCompiler_MainCode_By_LecturerID(Map<String, Object> mapCodeMainLecturer) {
        String lecturerID = (String) mapCodeMainLecturer.get("lecturerID");
        String code = (String) mapCodeMainLecturer.get("code");
        Lecturer lecturerFound = lecturerRepository.findLecturerByLecturerID(covertLectureID(lecturerID));
        if(lecturerFound != null) {
            CompileJavaCodeByLectureID compileJavaCodeByLectureID = new
                    CompileJavaCodeByLectureID(covertLectureID(lecturerID));
            return compileJavaCodeByLectureID.compileJavaCodeByLectureID(code);
        }
        return false;
    }
}

package com.example.backend_service.services;

import com.example.backend_service.models.Exam;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.models.ResultQuestionJavaCore;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_MarkStudentService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MarkStudentService implements I_MarkStudentService {

    private final MarkStudentRepository markStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    public MarkStudentService(MarkStudentRepository markStudentRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.markStudentRepository = markStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @Override
    public MarkStudent createMarkStudent(Map<String, Object> dataSubmitPost){
        String studentID = (String) dataSubmitPost.get("studentID");
        Integer examIDInt = (Integer) dataSubmitPost.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
        List<Map<String, Object>> answerQuestions = (List<Map<String, Object>>) dataSubmitPost.get("answerQuestions");
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        if(studentFound != null && examFound !=null ) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);

            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);

        }

        return null;
    }

    @Override
    public MarkStudent setMarkIs_Zero_If_Exam_Overdue(String studentID, Long examID) {
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        MarkStudent markStudentFound = markStudentRepository.findMarkStudentByExam_ExamID(examID);
        if(studentFound != null && examFound !=null && markStudentFound == null) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);
            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);
            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);
            return markStudentRepository.save(markStudent);
        }
        return null;
    }
}

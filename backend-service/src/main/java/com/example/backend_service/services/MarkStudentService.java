package com.example.backend_service.services;

import com.example.backend_service.models.*;
import com.example.backend_service.repositories.*;
import com.example.backend_service.services.i_service.I_MarkStudentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MarkStudentService implements I_MarkStudentService {

    private final MarkStudentRepository markStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    public MarkStudentService(MarkStudentRepository markStudentRepository, StudentRepository studentRepository, ExamRepository examRepository, BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository, QuestionJavaCoreExamRepository questionJavaCoreExamRepository) {
        this.markStudentRepository = markStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
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

            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);

            DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
            detailMarkStudent.setDetailMarkExam(0);
            //date time submit
            detailMarkStudent.setDateSubmitted(LocalDateTime.now());

            //set relationship
            markStudent.getDetailMarkStudents().add(detailMarkStudent);
            detailMarkStudent.setMarkStudent(markStudent);

            if(!answerQuestions.isEmpty()) {
                for(Map<String, Object> objectMap : answerQuestions) {
                    Integer questionJavaCoreExamIDInt = (Integer) objectMap.get("questionJavaCoreExamID");
                    Long questionJavaCoreExamID = questionJavaCoreExamIDInt != null ? questionJavaCoreExamIDInt.longValue() : null;
                    String codeStudentSubmitted = (String) objectMap.get("codeStudentSubmitted");

                    QuestionJavaCoreExam questionJavaCoreExamFound =
                            questionJavaCoreExamRepository.findQuestionJavaCoreExamsByQuestionJavaCoreExamID(questionJavaCoreExamID);
                    if(questionJavaCoreExamFound !=null) {
                        ResultQuestionJavaCore resultQuestionJavaCore = new ResultQuestionJavaCore();
                        resultQuestionJavaCore.setCodeStudentSubmitted(codeStudentSubmitted);

                        //Chỗ này sẽ xuất code output ra và set vào, nma thư nghiệm trước thì cho null trước
                        //resultQuestionJavaCore.setOutputCodeStudent(null);

                        //set relationship
                        questionJavaCoreExamFound.setResultQuestionJavaCore(resultQuestionJavaCore);
                        resultQuestionJavaCore.setQuestionJavaCoreExam(questionJavaCoreExamFound);

                        detailMarkStudent.getResultQuestionJavaCoreList().add(resultQuestionJavaCore);
                        resultQuestionJavaCore.setDetailMarkStudent(detailMarkStudent);

                        //save kq da code vao, neu code bi fail syntax, save la fail syntax
                        //suy nghi ki

                    }
                }
                return markStudentRepository.save(markStudent);
            }
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

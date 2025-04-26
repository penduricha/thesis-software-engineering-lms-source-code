package com.example.backend_service.services;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.Exam;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_MarkStudentServiceJavaClass;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class MarkStudentServiceJavaClass implements I_MarkStudentServiceJavaClass {

    private final MarkStudentRepository markStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    public MarkStudentServiceJavaClass(MarkStudentRepository markStudentRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.markStudentRepository = markStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }


    @Override
    public DetailMarkStudent createMarkStudent_And_DetailMarkStudent(Map<String, Object> dataPut) {
        String studentID = (String) dataPut.get("studentID");
        Integer examIDInt = (Integer) dataPut.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
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

            markStudentRepository.save(markStudent);
            return detailMarkStudent;
        }
        return null;
    }

    @Override
    public MarkStudent updateMarkStudent_Zero_If_Student_No_Submit_First(Map<String, Object> dataPut)
            throws JpaSystemException {
        String studentID = (String) dataPut.get("studentID");
        Integer examIDInt = (Integer) dataPut.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
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

            return markStudentRepository.save(markStudent);
        }
        return null;
    }

    @Override
    public DetailMarkStudent updateMarkStudent_Zero_If_Student_No_Submit_Retake
            (Map<String, Object> dataPut, String scoringMethod, MarkStudent markStudentFound) throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
        detailMarkStudent.setDetailMarkExam(0);
        //date time submit
        detailMarkStudent.setDateSubmitted(LocalDateTime.now());

        //set relationship
        markStudentFound.getDetailMarkStudents().add(detailMarkStudent);
        detailMarkStudent.setMarkStudent(markStudentFound);

        markStudentRepository.save(markStudentFound);
        return detailMarkStudent;
    }

}

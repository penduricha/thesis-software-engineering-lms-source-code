package com.example.backend_service.services;


import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService implements I_StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean persistStudents(List<Student> students) {
        return false;
    }

    @Override
    public List<Student> getStudents() {
        return List.of();
    }

    @Override
    public Student findStudentByStudentId(String studentID) throws JpaSystemException {
        return studentRepository.findStudentByStudentID(studentID);
    }

    @Override
    public Student updatePasswordStudent(String studentID, String newPassword) {
        Student studentFind = findStudentByStudentId(studentID);
        if(studentFind != null) {
            studentFind.setPassword(newPassword);
            return studentRepository.save(studentFind);
        }
        return null;
    }

    @Override
    public Map<String,Object> findStudentInformation_By_StudentID(String studentID) {
        Student studentFind = findStudentByStudentId(studentID);
        Map<String, Object> studentInfo = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(studentFind != null) {
            studentInfo.put("studentID", studentFind.getStudentID());
            studentInfo.put("lastName", studentFind.getLastName());
            studentInfo.put("firstName", studentFind.getFirstName());
            studentInfo.put("gender", studentFind.isGender() ? "Male" : "Female");
            //format date of birth
            LocalDate dateOfBirth = studentFind.getDateOfBirth();
            studentInfo.put("dateOfBirth", dateOfBirth.format(formatter));
            return studentInfo;
        }
        return null;
    }

}

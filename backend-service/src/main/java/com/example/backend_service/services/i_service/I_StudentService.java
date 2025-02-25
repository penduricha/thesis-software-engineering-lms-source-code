package com.example.backend_service.services.i_service;


import com.example.backend_service.models.*;

import java.util.List;
import java.util.Map;

public interface I_StudentService {
    public boolean persistStudents(List<Student> students);

    public List<Student> getStudents();

    public Student findStudentByStudentId(String studentID);

    public Student updatePasswordStudent(String studentID, String newPassword);

    public Map<String,Object> findStudentInformation_By_StudentID(String studentID);

    public Long getCourseIDByStudentID(String studentID);

    public List<Map<String, Object>> getExams_Calendar_Student_By_StartDate(String studentID,int yearStartDate, int monthStartDate, int dateStartDate);
}

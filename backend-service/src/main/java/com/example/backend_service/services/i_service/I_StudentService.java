package com.example.backend_service.services.i_service;


import com.example.backend_service.models.*;

import java.util.List;

public interface I_StudentService {
    public boolean persistStudents(List<Student> students);

    public List<Student> getStudents();

    public Student findStudentByStudentId(String studentID);
}

package com.example.backend_service.controllers;




import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class StudentController {
    private final StudentService studentService;

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/studentID_password/{studentID}")
    public Map<String, Object> findStudentByStudentId(@PathVariable String studentID) throws HttpClientErrorException{
        Map<String, Object> studentMap = new HashMap<>();
        Student student = studentService.findStudentByStudentId(studentID);
        if (student != null) {
            studentMap.put("studentID",student.getStudentID());
            studentMap.put("password",student.getPassword());
        }
        return studentMap;
    }

    @GetMapping("/student/studentID_name/{studentID}")
    public Map<String, Object> findStudentID_Name_ByStudentId(@PathVariable String studentID) throws HttpClientErrorException{
        Map<String, Object> studentNameMap = new HashMap<>();
        Student student = studentService.findStudentByStudentId(studentID);
        if (student != null) {
            studentNameMap.put("studentID",student.getStudentID());
            studentNameMap.put("lastName",student.getLastName());
            studentNameMap.put("firstName",student.getFirstName());
        }
        return studentNameMap;
    }
}

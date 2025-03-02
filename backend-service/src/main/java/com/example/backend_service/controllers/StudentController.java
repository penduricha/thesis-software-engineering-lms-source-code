package com.example.backend_service.controllers;


import com.example.backend_service.models.CodeStorageSave;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.CodeStorageSaveService;
import com.example.backend_service.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.*;

@RestController
@RequestMapping("/api")
@ResponseBody
public class StudentController {
    private final StudentService studentService;

//    private final StudentRepository studentRepository;

    private final CodeStorageSaveService codeStorageSaveService;

    @Autowired
    public StudentController(StudentService studentService, CodeStorageSaveService codeStorageSaveService) {
        this.studentService = studentService;
        this.codeStorageSaveService = codeStorageSaveService;
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

    @GetMapping("/student/getPassword_By_StudentID/{studentID}")
    public ResponseEntity<String> updatePasswordStudent(@PathVariable String studentID) throws HttpClientErrorException {
        Student student = studentService.findStudentByStudentId(studentID);
        return ResponseEntity.ok(student.getPassword());
    }

    @PatchMapping("/student/updatePasswordStudent/{studentID}/{newPassword}")
    public ResponseEntity<String> updatePasswordStudent(@PathVariable String studentID, @PathVariable String newPassword) throws HttpClientErrorException {
        Student updatedStudent = studentService.updatePasswordStudent(studentID, newPassword);
        return ResponseEntity.ok(updatedStudent.getPassword());
    }

    @GetMapping("/student/get_course_id_by_student_id/{studentID}")
    public ResponseEntity<Long> getCourseID_By_StudentID(@PathVariable String studentID) throws HttpClientErrorException {
        return ResponseEntity.ok(studentService.getCourseIDByStudentID(studentID));
    }

    @PostMapping("/student/student_access_exam_post/{studentID}/{examID}")
    public ResponseEntity<Long>
    postAccessToExam_By_StudentID_ExamID(@PathVariable String studentID,@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(studentService.accessToExam_By_StudentID_ExamID(studentID, examID));
    }

    //22662361 23
    @DeleteMapping("/student/student_access_exam_delete/{studentID}/{examID}")
    public ResponseEntity<String>
    deleteAccessToExam_By_StudentID_ExamID
            (@PathVariable String studentID, @PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(studentService.deleteAccessToExam_By_StudentID(studentID, examID));
    }

    //find student access exam, use examID = 23
    @GetMapping("/student/find_student_access_exam/{examID}")
    public ResponseEntity<Boolean>
        findStudent_Access_Exam_By_ExamID(@PathVariable Long examID) throws HttpClientErrorException{
        //System.out.println(studentService.findStudent_Access_Exam_By_ExamID(examID));
        //map tra new HashMap
        Boolean studentAccessExam = !Objects.equals(studentService.findStudent_Access_Exam_By_ExamID(examID), new HashMap<>());
        return ResponseEntity.ok(studentAccessExam);
    }

    @PostMapping("/student/save_code_by_studentID/{studentID}")
    public ResponseEntity<CodeStorageSave>
    save_Code_Do_Exam(@RequestBody CodeStorageSave codeStorageSave,@PathVariable String studentID) throws HttpClientErrorException{
        return ResponseEntity.ok(codeStorageSaveService.saveCode(codeStorageSave, studentID));
    }

    @GetMapping("/student/get_code_by_studentID_indexQuestion/{studentID}/{indexQuestion}")
    public ResponseEntity<String>
    getCodeSave_By_StudentID_IndexQuestion(@PathVariable String studentID, @PathVariable int indexQuestion) throws HttpClientErrorException{
        return ResponseEntity
                .ok(codeStorageSaveService.getCodeSave_By_StudentID_IndexQuestion(studentID, indexQuestion));
    }
}

package com.example.backend_service.controllers;

import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.services.MarkStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkStudentController {
    private final MarkStudentService markStudentService;

    private final MarkStudentRepository markStudentRepository;

    public MarkStudentController(MarkStudentService markStudentService, MarkStudentRepository markStudentRepository) {
        this.markStudentService = markStudentService;
        this.markStudentRepository = markStudentRepository;
    }

    @GetMapping("/mark_student/set_mark_zero/{studentID}/{examID}")
    public ResponseEntity<MarkStudent>
    setMarkIs_Zero_If_Exam_Overdue(@PathVariable String studentID,@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(markStudentService.setMarkIs_Zero_If_Exam_Overdue(studentID, examID));
    }

    @PostMapping("/mark_student/post-mark-student-after-submit-exam-java-core")
    public ResponseEntity<MarkStudent> postMarkStudent_Java_Core_Exam(@RequestBody Map<String, Object> dataSubmitPost) throws HttpClientErrorException {
        String studentID = (String) dataSubmitPost.get("studentID");
        Integer examIDInt = (Integer) dataSubmitPost.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
        String scoringMethod = markStudentRepository.getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake(studentID, examID);
        if(scoringMethod == null) {
            return ResponseEntity.ok(markStudentService.createMarkStudent(dataSubmitPost));
        }
        return ResponseEntity.ok(null);
    }
}

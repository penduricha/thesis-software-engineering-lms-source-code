package com.example.backend_service.controllers;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.services.ExamService;
import com.example.backend_service.services.MarkStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkStudentController {
    private final MarkStudentService markStudentService;

    private final MarkStudentRepository markStudentRepository;

    private final ExamService examService;

    public MarkStudentController(MarkStudentService markStudentService, MarkStudentRepository markStudentRepository, ExamService examService) {
        this.markStudentService = markStudentService;
        this.markStudentRepository = markStudentRepository;
        this.examService = examService;
    }

    @GetMapping("/mark_student/set_mark_zero/{studentID}/{examID}")
    public ResponseEntity<MarkStudent>
    setMarkIs_Zero_If_Exam_Overdue(@PathVariable String studentID,@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(markStudentService.setMarkIs_Zero_If_Exam_Overdue(studentID, examID));
    }

    @PostMapping("/mark_student/post-mark-student-after-submit-exam-java-core")
    public ResponseEntity<?> postMarkStudent_Java_Core_Exam(@RequestBody Map<String, Object> dataSubmitPost)
            throws HttpClientErrorException{
        try {
            MarkStudent markStudent = markStudentService.setMarkExam_After_SetDetailMarkExam(dataSubmitPost);
            return ResponseEntity.ok(markStudent);
        } catch (HttpClientErrorException e) {
            // Handle specific HTTP client errors
            return ResponseEntity.status(e.getStatusCode()).body(e.getStatusText());
        } catch (Exception e) {
            // Handle general exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/mark-student-by-examID-id/{examID}")
    public ResponseEntity<Void> deleteMarkStudentID_By_MarkStudentID(@PathVariable Long examID)
            throws HttpClientErrorException{
        return ResponseEntity.ok(markStudentService.deleteMarkStudent_By_ExamID(examID));
    }
}

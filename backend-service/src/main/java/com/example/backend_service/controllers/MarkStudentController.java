package com.example.backend_service.controllers;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.services.ExamService;
import com.example.backend_service.services.MarkStudentService;
import com.example.backend_service.services.MarkStudentServiceJavaClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkStudentController {
    private final MarkStudentService markStudentService;

    private final MarkStudentRepository markStudentRepository;

    private final MarkStudentServiceJavaClass markStudentServiceJavaClass;

    private final ExamRepository examRepository;

    private final ExamService examService;

    public MarkStudentController(MarkStudentService markStudentService, MarkStudentRepository markStudentRepository, MarkStudentServiceJavaClass markStudentServiceJavaClass, ExamRepository examRepository, ExamService examService) {
        this.markStudentService = markStudentService;
        this.markStudentRepository = markStudentRepository;
        this.markStudentServiceJavaClass = markStudentServiceJavaClass;
        this.examRepository = examRepository;
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
            String studentID = (String) dataSubmitPost.get("studentID");
            Integer examIDInt = (Integer) dataSubmitPost.get("examID");
            Long examID = examIDInt != null ? examIDInt.longValue() : null;
            String scoringMethod = markStudentRepository
                    .getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake(studentID, examID);
            if (scoringMethod == null || scoringMethod.isEmpty()) {
                //Trường hợp nếu làm ktra lan dau tien và ko làm lại
                MarkStudent markStudent = markStudentService.setMarkExam_After_SetDetailMarkExam(dataSubmitPost);
                return ResponseEntity.ok(markStudent);
            } else {
                //trich record mark student
                MarkStudent markStudentFound = examRepository.findMarkStudent_By_StudentID_ExamID(studentID, examID);
                if(markStudentFound == null) {
                    MarkStudent markStudent;
                    //van tao transaction giong nhu bai ktra 1 lan
                    markStudent = markStudentService.setMarkExam_After_SetDetailMarkExam(dataSubmitPost);
                    return ResponseEntity.ok(markStudent);
                } else {
                    //transaction retake exam
                    DetailMarkStudent detailMarkStudent;
                    detailMarkStudent = markStudentService
                            .setMarkExam_After_SetDetailMarkExam_RetakeExam(dataSubmitPost, markStudentFound, scoringMethod);
                    return ResponseEntity.ok(detailMarkStudent);
                }

            }
        } catch (HttpClientErrorException e) {
            // Handle specific HTTP client errors
            return ResponseEntity.status(e.getStatusCode()).body(e.getStatusText());
        } catch (Exception e) {
            // Handle general exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/mark_student/put-mark-student-zero-java-class-if-no-submit")
    public ResponseEntity<?> putMarkStudent_Zero_JavaClass_If_No_Submitted(@RequestBody Map<String, Object> dataPut)
            throws HttpClientErrorException{
        try {
            String studentID = (String) dataPut.get("studentID");
            Integer examIDInt = (Integer) dataPut.get("examID");
            Long examID = examIDInt != null ? examIDInt.longValue() : null;
            String scoringMethod = markStudentRepository
                    .getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake(studentID, examID);
            if (scoringMethod == null || scoringMethod.isEmpty()) {
                //Trường hợp nếu làm ktra lan dau tien và ko làm lại
                MarkStudent markStudent = markStudentServiceJavaClass.
                        updateMarkStudent_Zero_If_Student_No_Submit_First(dataPut);
                return ResponseEntity.ok(markStudent);
            } else {
                //trich record mark student
                MarkStudent markStudentFound = examRepository.findMarkStudent_By_StudentID_ExamID(studentID,examID);
                if(markStudentFound == null) {
                    MarkStudent markStudent;
                    //van tao transaction giong nhu bai ktra 1 lan
                    markStudent = markStudentServiceJavaClass.
                            updateMarkStudent_Zero_If_Student_No_Submit_First(dataPut);
                    return ResponseEntity.ok(markStudent);
                } else {
                    //transaction retake exam
                    DetailMarkStudent detailMarkStudent;
                    detailMarkStudent = markStudentServiceJavaClass
                            .updateMarkStudent_Zero_If_Student_No_Submit_Retake(dataPut, scoringMethod, markStudentFound);
                    return ResponseEntity.ok(detailMarkStudent);
                }

            }
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

    @GetMapping("/mark_student/get-list-mark-student-by-student-id/{studentID}")
    public ResponseEntity<List<Map<String, Object>>> getListResultExam_By_StudentID(@PathVariable String studentID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(markStudentService.getListResultExam_By_StudentID(studentID));
    }

    @GetMapping("/mark_student/get-list-detail-mark-student-by-mark-student-id/{markStudentID}")
    public ResponseEntity<List<Map<String, Object>>>
        getListDetailMarkStudent_By_MarkStudentID(@PathVariable Long markStudentID)
            throws HttpClientErrorException{
        return ResponseEntity.ok(markStudentService.getListDetailMarkStudent_By_MarkStudentID(markStudentID));
    }

    @GetMapping("/mark_student/get-list-mark-student-by-exam-id/{examID}")
    public ResponseEntity<List<Map<String, Object>>> getListStudentMark_By_ExamID(@PathVariable Long examID) {
        return ResponseEntity.ok(markStudentService.getListStudentMark_By_ExamID(examID));
    }

    @GetMapping("/mark_student/get-average-mark-exam-by-exam-id/{examID}")
    public ResponseEntity<Double> getAverageMarkExam_By_ExamID(@PathVariable Long examID)
            throws HttpClientErrorException{
        return ResponseEntity.ok(markStudentService.getAverageMarkExam_By_ExamID(examID));
    }

    @GetMapping("/mark_student/get-group-by-mark-exam-by-exam-id/{examID}")
    public ResponseEntity<List<Map<String, Object>>> getGroupByMarkExam_By_ExamID(@PathVariable Long examID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(markStudentService.getGroupByMarkExam_By_ExamID(examID));
    }

    /*
    @Override
    public Double getAverageMarkExam_By_ExamID(Long examID) throws JpaSystemException{
        List<Map<String, Object>> listMarkStudentByExamID = getListStudentMark_By_ExamID(examID);
        if(!listMarkStudentByExamID.isEmpty()) {
            return markStudentRepository.getAverageMarkExam_By_ExamID(examID);
        }
        return 0.0;
    }


     */
}

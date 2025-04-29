package com.example.backend_service.controllers;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.repositories.MarkStudentRepository;
import org.springframework.http.ResponseEntity;
import com.example.backend_service.services.MarkStudentServiceJavaClass;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MarkStudentJavaClassController {

    private final MarkStudentRepository markStudentRepository;

    private final MarkStudentServiceJavaClass markStudentServiceJavaClass;

    public MarkStudentJavaClassController(MarkStudentRepository markStudentRepository, MarkStudentServiceJavaClass markStudentServiceJavaClass) {
        this.markStudentRepository = markStudentRepository;
        this.markStudentServiceJavaClass = markStudentServiceJavaClass;
    }

    @PostMapping("/mark_student/post-mark-student-after-submit-exam-java-class")
    public ResponseEntity<?> postResultJavaClass_From_FormResult(@RequestBody Map<String, Object> dataPost)
            throws HttpClientErrorException {
        /*
        form data post
        {
          "studentID": 21107602,
        "examID" :10,
           "totalScore": 8.0,
          "detailAnswers": [
            {
              "sentence": "Câu 1",
              "scoreAchievement": 2.0,
              "maxScore": 2.0,
              "reviews": "Hoàn thành đầy đủ yêu cầu: Class Event đúng, có phương thức getCostPerParticipant, getter/setter, constructor, và toString. Ràng buộc toàn vẹn dữ liệu được đảm bảo."
            },
            {
              "sentence": "Câu 2",
              "scoreAchievement": 6.0,
              "maxScore": 6.5,
              "reviews": "Thiếu phương thức addEvent trong class EventList. Các phương thức còn lại (updateEvent, getEventsWithMaxParsInYear) hoạt động đúng yêu cầu."
            },
            {
              "sentence": "Câu 3",
              "scoreAchievement": 1.5,
              "maxScore": 1.5,
              "reviews": "Hoàn thành đầy đủ yêu cầu: Tạo danh sách 5 sự kiện và kiểm thử các chức năng của EventList."
            }
          ]
        }

         */
        try {
            String studentID = (String) dataPost.get("studentID");
            Integer examIDInt = (Integer) dataPost.get("examID");
            Long examID = examIDInt != null ? examIDInt.longValue() : null;
            String scoringMethod = markStudentRepository
                    .getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake(studentID, examID);
            if (scoringMethod == null || scoringMethod.isEmpty()) {
                //Trường hợp nếu làm ktra lan dau tien và ko làm lại
                MarkStudent markStudent = markStudentServiceJavaClass.saveResultJavaClassFirst(dataPost);
                return ResponseEntity.ok(markStudent);
            } else {
                //trich record mark student
                MarkStudent markStudentFound = markStudentRepository.findMarkStudentByExam_ExamID(examID);
                if(markStudentFound == null) {
                    //MarkStudent markStudent;
                    //van tao transaction giong nhu bai ktra 1 lan
                    MarkStudent markStudent = markStudentServiceJavaClass.saveResultJavaClassFirst(dataPost);
                    return ResponseEntity.ok(markStudent);
                } else {
                    //transaction retake exam
                    DetailMarkStudent detailMarkStudent;
                    detailMarkStudent = markStudentServiceJavaClass
                            .saveResultJavaClassRetake(dataPost, markStudentFound, scoringMethod);
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

}

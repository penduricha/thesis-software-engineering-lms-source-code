package com.example.backend_service.controllers;

import com.example.backend_service.models.Exam;
import com.example.backend_service.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExamController {

    private final ExamService examService;


    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/post_exam/{courseID}")
    public ResponseEntity<Exam> post_Exam (@RequestBody Map<String,Object> mapExam, @PathVariable Long courseID) throws HttpClientErrorException {
        String titleExam = (String) mapExam.get("titleExam");
        String typeExam = (String) mapExam.get("typeExam");
        String topicExam = (String) mapExam.get("topicExam");
        String retakeExamValue = (String) mapExam.get("retakeExam");
        boolean retakeExam = "Yes".equalsIgnoreCase(retakeExamValue);
        String scoringMethod = (String) mapExam.get("scoringMethod");
        int duration = (Integer) mapExam.get("duration");

        LocalDateTime startDate = LocalDateTime.of(
                (Integer) mapExam.get("startDateYear"),
                (Integer) mapExam.get("startDateMonth"),
                (Integer) mapExam.get("startDateDay"),
                (Integer) mapExam.get("startDateHour"),
                (Integer) mapExam.get("startDateMinute"),
                0
        );

        // Extracting end date and time
        LocalDateTime endDate = LocalDateTime.of(
                (Integer) mapExam.get("endDateYear"),
                (Integer) mapExam.get("endDateMonth"),
                (Integer) mapExam.get("endDateDay"),
                (Integer) mapExam.get("endDateHour"),
                (Integer) mapExam.get("endDateMinute"),
                0
        );

        String linkExamPaper = (String) mapExam.get("linkExamPaper");
        String passwordExam = (String) mapExam.get("passwordExam");

        Exam exam = new Exam();
        exam.setTitleExam(titleExam);
        exam.setTypeExam(typeExam); // Assuming there is a setType method
        exam.setTopicExam(topicExam); // Assuming there is a setTopic method
        exam.setRetakeExam(retakeExam);
        exam.setScoringMethod(scoringMethod); // Assuming there is a setScoringMethod method
        exam.setDuration(duration);
        exam.setStartDate(startDate); // Assuming there is a setStartDate method
        exam.setEndDate(endDate); // Assuming there is a setEndDate method
        exam.setLinkExamPaper(linkExamPaper); // Assuming there is a setLinkExamPaper method
        exam.setPasswordExam(passwordExam); // Assuming there is a setPasswordExam method

        //public Exam(String titleExam,
        // String typeExam, String topicExam,
        // boolean retakeExam,
        // String scoringMethod,
        // int duration,
        // LocalDateTime startDate,
        // LocalDateTime endDate,
        // String linkExamPaper,
        // String passwordExam)

        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(exam,courseID));
    }
}

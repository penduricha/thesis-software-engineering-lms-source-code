package com.example.backend_service.controllers;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.BankTestCaseJavaCore;
import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.services.ExamService;
import com.example.backend_service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExamController {

    private final ExamService examService;

    private final StudentService studentService;

    public ExamController(ExamService examService, StudentService studentService) {
        this.examService = examService;
        this.studentService = studentService;
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
        boolean viewTable = (Boolean) mapExam.get("viewTable");

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
        exam.setViewTable(viewTable);

        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(exam,courseID));
    }

    @PostMapping("/post_exam_java_core_random_question/{courseID}/{numberOfQuestions}")
    public ResponseEntity<Exam> post_Exam_JavaCore_With_RandomQuestion
            (@RequestBody Map<String,Object> mapExam, @PathVariable Long courseID, @PathVariable int numberOfQuestions) throws HttpClientErrorException {
        String titleExam = (String) mapExam.get("titleExam");
        String typeExam = (String) mapExam.get("typeExam");
        String topicExam = (String) mapExam.get("topicExam");
        String retakeExamValue = (String) mapExam.get("retakeExam");
        boolean retakeExam = "Yes".equalsIgnoreCase(retakeExamValue);
        String scoringMethod = (String) mapExam.get("scoringMethod");
        int duration = (Integer) mapExam.get("duration");
        boolean viewTable = (Boolean) mapExam.get("viewTable");

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
        exam.setViewTable(viewTable);

        return ResponseEntity.status(HttpStatus.CREATED).body(examService
                .createExam_JavaCore_With_RandomQuestion(exam,courseID,numberOfQuestions));
    }

    @PostMapping("/post_exam_java_core_with_choose_questions/{courseID}")
    public ResponseEntity<Exam> post_Exam_JavaCore_With_Choose_Questions
            (@RequestBody Map<String,Object> mapExamPost,
             @PathVariable Long courseID)
            throws HttpClientErrorException {
        String titleExam = (String) mapExamPost.get("titleExam");
        String typeExam = (String) mapExamPost.get("typeExam");
        String topicExam = (String) mapExamPost.get("topicExam");
        String retakeExamValue = (String) mapExamPost.get("retakeExam");
        boolean retakeExam = "Yes".equalsIgnoreCase(retakeExamValue);
        String scoringMethod = (String) mapExamPost.get("scoringMethod");
        int duration = (Integer) mapExamPost.get("duration");
        boolean viewTable = (Boolean) mapExamPost.get("viewTable");

        LocalDateTime startDate = LocalDateTime.of(
                (Integer) mapExamPost.get("startDateYear"),
                (Integer) mapExamPost.get("startDateMonth"),
                (Integer) mapExamPost.get("startDateDay"),
                (Integer) mapExamPost.get("startDateHour"),
                (Integer) mapExamPost.get("startDateMinute"),
                0
        );

        // Extracting end date and time
        LocalDateTime endDate = LocalDateTime.of(
                (Integer) mapExamPost.get("endDateYear"),
                (Integer) mapExamPost.get("endDateMonth"),
                (Integer) mapExamPost.get("endDateDay"),
                (Integer) mapExamPost.get("endDateHour"),
                (Integer) mapExamPost.get("endDateMinute"),
                0
        );

        String linkExamPaper = (String) mapExamPost.get("linkExamPaper");
        String passwordExam = (String) mapExamPost.get("passwordExam");
        List<Map<String, Object>> questionJavaCoreExams =
                (List<Map<String, Object>> ) mapExamPost.get("questionJavaCoreExams");


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
        exam.setViewTable(viewTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                examService.createExam_JavaCore_With_ChooseQuestion(exam, courseID, questionJavaCoreExams));
    }

    @PostMapping("/post_exam_java_class_with_choose_test/{courseID}")
    public ResponseEntity<Exam> createExam_JavaClass_With_ChooseTest(@RequestBody Map<String,Object> mapExamPost,@PathVariable Long courseID) {
        String titleExam = (String) mapExamPost.get("titleExam");
        String typeExam = (String) mapExamPost.get("typeExam");
        String topicExam = (String) mapExamPost.get("topicExam");
        String retakeExamValue = (String) mapExamPost.get("retakeExam");
        boolean retakeExam = "Yes".equalsIgnoreCase(retakeExamValue);
        String scoringMethod = (String) mapExamPost.get("scoringMethod");
        int duration = (Integer) mapExamPost.get("duration");
        boolean viewTable = (Boolean) mapExamPost.get("viewTable");
        Integer bankTestJavaOopIDInt = (Integer) mapExamPost.get("bankTestJavaOopID");
        Long bankTestJavaOopID = bankTestJavaOopIDInt != null ? bankTestJavaOopIDInt.longValue() : null;

        LocalDateTime startDate = LocalDateTime.of(
                (Integer) mapExamPost.get("startDateYear"),
                (Integer) mapExamPost.get("startDateMonth"),
                (Integer) mapExamPost.get("startDateDay"),
                (Integer) mapExamPost.get("startDateHour"),
                (Integer) mapExamPost.get("startDateMinute"),
                0
        );

        // Extracting end date and time
        LocalDateTime endDate = LocalDateTime.of(
                (Integer) mapExamPost.get("endDateYear"),
                (Integer) mapExamPost.get("endDateMonth"),
                (Integer) mapExamPost.get("endDateDay"),
                (Integer) mapExamPost.get("endDateHour"),
                (Integer) mapExamPost.get("endDateMinute"),
                0
        );

        String linkExamPaper = (String) mapExamPost.get("linkExamPaper");
        String passwordExam = (String) mapExamPost.get("passwordExam");
//        List<Map<String, Object>> questionJavaCoreExams =
//                (List<Map<String, Object>> ) mapExamPost.get("questionJavaCoreExams");

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
        exam.setViewTable(viewTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                examService.createExam_JavaClass_With_ChooseTest(exam, courseID, bankTestJavaOopID));
    }

    @GetMapping("/get_exam_by_course_id/{courseID}")
    public ResponseEntity<List<Map<String, Object>>> getExamsByCourseID(@PathVariable Long courseID) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.getExamsByCourseID(courseID));
    }

    @GetMapping("/view_exam_by_course_id_exam_id/{examID}/{courseID}")
    public ResponseEntity<Map<String, Object>> viewExam_By_ExamID(@PathVariable Long examID,@PathVariable Long courseID) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.viewExam_By_ExamID(examID, courseID));
    }

    @GetMapping("/view_exam_by_course_id_exam_id_student_exam_before/{examID}/{courseID}")
    public ResponseEntity<Map<String, Object>>
        view_Information_Exam_Before_Student(@PathVariable Long examID,@PathVariable Long courseID) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.view_Information_Exam_Before_Student(examID, courseID));
    }

    @GetMapping("/view_exam_by_calendar_lecture/{lectureID}/{yearStartDate}/{monthStartDate}/{dateStartDate}")
    public ResponseEntity<List<Map<String, Object>>>
        getExams_Calendar_Lecture_By_StartDate(@PathVariable String lectureID,
                                           @PathVariable int yearStartDate,
                                           @PathVariable int monthStartDate,
                                           @PathVariable int dateStartDate) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.
                getExams_Calendar_Lecture_By_StartDate(covertLectureID(lectureID), yearStartDate, monthStartDate, dateStartDate));
    }

    @GetMapping("/view_exam_by_calendar_student/{studentID}/{yearStartDate}/{monthStartDate}/{dateStartDate}")
    public ResponseEntity<List<Map<String, Object>>>
    getExams_Calendar_Student_By_StartDate(@PathVariable String studentID,
                                           @PathVariable int yearStartDate,
                                           @PathVariable int monthStartDate,
                                           @PathVariable int dateStartDate) throws HttpClientErrorException {
        return ResponseEntity.ok(studentService
                .getExams_Calendar_Student_By_StartDate(studentID, yearStartDate, monthStartDate, dateStartDate));
    }

    public String covertLectureID(String lectureID) {
        return LectureIDFunc.covertLectureID(lectureID);
    }

    @DeleteMapping("/delete_exam/{examID}")
    public ResponseEntity<Long> deleteExam_By_ExamID(@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.deleteExam_By_ExamID(examID));
    }

    @PutMapping("/put_exam/{examID}")
    public ResponseEntity<Exam> put_Exam (@RequestBody Map<String,Object> mapExamToPut,
                                          @PathVariable Long examID) throws HttpClientErrorException {

        String titleExam = (String) mapExamToPut.get("titleExam");
        String typeExam = (String) mapExamToPut.get("typeExam");
        String topicExam = (String) mapExamToPut.get("topicExam");
        String retakeExamValue = (String) mapExamToPut.get("retakeExam");
        boolean retakeExam = "Yes".equalsIgnoreCase(retakeExamValue);
        String scoringMethod = (String) mapExamToPut.get("scoringMethod");
        int duration = (Integer) mapExamToPut.get("duration");
        boolean viewTable = (Boolean) mapExamToPut.get("viewTable");

        LocalDateTime startDate = LocalDateTime.of(
                (Integer) mapExamToPut.get("startDateYear"),
                (Integer) mapExamToPut.get("startDateMonth"),
                (Integer) mapExamToPut.get("startDateDay"),
                (Integer) mapExamToPut.get("startDateHour"),
                (Integer) mapExamToPut.get("startDateMinute"),
                0
        );

        // Extracting end date and time
        LocalDateTime endDate = LocalDateTime.of(
                (Integer) mapExamToPut.get("endDateYear"),
                (Integer) mapExamToPut.get("endDateMonth"),
                (Integer) mapExamToPut.get("endDateDay"),
                (Integer) mapExamToPut.get("endDateHour"),
                (Integer) mapExamToPut.get("endDateMinute"),
                0
        );

        String linkExamPaper = (String) mapExamToPut.get("linkExamPaper");
        String passwordExam = (String) mapExamToPut.get("passwordExam");

        Exam exam = new Exam();
        exam.setExamID(examID);
        exam.setTitleExam(titleExam);
        exam.setTypeExam(typeExam);
        exam.setTopicExam(topicExam);
        exam.setRetakeExam(retakeExam);
        exam.setScoringMethod(scoringMethod);
        exam.setDuration(duration);
        exam.setStartDate(startDate);
        exam.setEndDate(endDate);
        exam.setLinkExamPaper(linkExamPaper);
        exam.setPasswordExam(passwordExam);
        exam.setViewTable(viewTable);
        return ResponseEntity.ok(examService.updateExam_By_ExamID(exam,examID));
    }

    @GetMapping("/find-title-exam-by-course-id-title-exam/{courseID}/{titleExam}")
    public ResponseEntity<Boolean> findTitleExam_By_TitleExam_ExamID
            (@PathVariable Long courseID,@PathVariable String titleExam)
            throws HttpClientErrorException{
        String titleExamFound = examService.getTitle_Exam_By_CourseID(courseID,titleExam);
        if(titleExamFound != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/exam/get-is-view-table-by-exam-id/{examID}")
    public ResponseEntity<Boolean> getViewTable_From_ExamID(@PathVariable Long examID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(examService.getViewTable_From_ExamID(examID));
    }

    @GetMapping("/exam/get-list-title-exam")
    //sửa thành by gv id lectureID
    public ResponseEntity<List<String>> getListTitleExam() throws HttpClientErrorException {
        return ResponseEntity.ok(examService.getListTitleExam());
    }

    @GetMapping("/exam/get-topic-exam-by-exam-id/{examID}")
    public ResponseEntity<String> getTopicExam_By_ExamID(@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(examService.getTopicExam_By_ExamID(examID));
    }
}

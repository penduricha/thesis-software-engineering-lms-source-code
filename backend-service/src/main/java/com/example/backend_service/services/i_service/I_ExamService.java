package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface I_ExamService {
    public Exam createExam(Exam exam, Long courseID);

    public Exam createExam_JavaCore_With_ChooseQuestion(Exam exam,
                                                        Long courseID,
                                                        List<Map<String, Object>> questionJavaCoreExams
    );

    public List<Exam> getExamsByCourseID(Long courseID);

    public Exam getExamByID(Long examID);

    public Exam createExam_JavaCore_With_RandomQuestion(Exam exam, Long courseID, int numberOfQuestions);

    public Exam createExam_JavaClass_With_ChooseTest(Exam exam, Long courseID, Long bankTestJavaOopID);

    public List<Map<String, Object>> getExamsByCourseID_And_StudentID(Long courseID, String studentID);

    public Map<String, Object> viewExam_By_ExamID(Long examID, Long courseID, String studentID);

    public Map<String, Object> view_Information_Exam_Before_Student(Long examID, Long courseID, String studentID);

    public Long deleteExam_By_ExamID(Long examID);

    public Exam findExam_By_ExamID(Long examID);

    public Exam updateExam_By_ExamID(Exam exam, Long examID);

    public List<Map<String, Object>>
    getExams_Calendar_Lecturer_By_StartDate(String lecturerID,int yearStartDate, int monthStartDate, int dateStartDate);

    //public Map<String, Object> get_Student_StartDate_Exam_By_StudentID (String studentID);

    public Map<String, Object> get_Status_Retake_And_Scoring_Method_By_ExamID(Long examID);

    public String getTitle_Exam_By_CourseID(Long courseID, String titleExam);

    public Boolean getViewTable_From_ExamID(Long examID);

    public List<String> getListTitleExam();

    public String getTopicExam_By_ExamID(Long examID);
}

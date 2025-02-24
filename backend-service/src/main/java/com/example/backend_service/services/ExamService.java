package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.Course;
import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.services.i_service.I_ExamService;
import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamService implements I_ExamService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ExamRepository examRepository;

    private final CourseRepository courseRepository;

    private final BankQuestionJavaCoreService bankQuestionJavaCoreService;

    public ExamService(ExamRepository examRepository, CourseRepository courseRepository, BankQuestionJavaCoreService bankQuestionJavaCoreService) {
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.bankQuestionJavaCoreService = bankQuestionJavaCoreService;
    }

    @Override
    public Exam createExam(Exam exam, Long courseID) throws JpaSystemException {
        // Retrieve the course using the provided courseID
        Course course = courseRepository.findCourseByCourseID(courseID);
        String javaCore = "Java Core";
        if(exam.getTopicExam().equalsIgnoreCase(javaCore)) {
            List<Map<String, Object>> questionRandomJavaCore = bankQuestionJavaCoreService.getRandom_10_Questions_JavaCore();
            if (course != null && !questionRandomJavaCore.isEmpty()) {
                // Set initial properties for the exam
                exam.setStudentAccess(false);
                course.getExams().add(exam);
                exam.setCourse(course);
                // Iterate over the list of random questions
                for (Map<String, Object> questionMap : questionRandomJavaCore) {
                    Long questionJavaCoreID = (Long) questionMap.get("questionJavaCoreID");
                    String contentQuestion = (String) questionMap.get("contentQuestion");
                    String codeSample = (String) questionMap.get("codeSample");
                    // Create the BankQuestionJavaCore instance for this specific question
                    BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
                    bankQuestionJavaCore.setQuestionJavaCoreID(questionJavaCoreID);

                    // Create the QuestionJavaCoreExam instance
                    QuestionJavaCoreExam questionJavaCoreExam = new QuestionJavaCoreExam();
                    questionJavaCoreExam.setContentQuestion(contentQuestion);
                    questionJavaCoreExam.setCodeSample(codeSample);

                    exam.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                    questionJavaCoreExam.setExam(exam);
                    questionJavaCoreExam.setBankQuestionJavaCore(bankQuestionJavaCore);

                    // Associate the question with its bank
                    bankQuestionJavaCore.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                }
                return examRepository.save(exam);
            }
            return null;
        }
        // Return null if course or questions are not found
        return null;
    }

    @Override
    public List<Map<String, Object>> getExamsByCourseID(Long courseID) throws JpaSystemException {
        List<Map<String, Object>> queryList = examRepository.getExamsByCourseID(courseID);
        List<Map<String, Object>> convertedList = new ArrayList<>();
        if(!queryList.isEmpty()) {
            for (Map<String, Object> queryMap : queryList) {
                Map<String, Object> convertedMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String convertedKey = ConvertNameField.convertSnakeToCamel(key);
                    if (!convertedKey.equals("examId")) {
                        convertedMap.put(convertedKey, value);
                    } else {
                        convertedMap.put("examID", value);
                    }
                }
                convertedList.add(convertedMap);
            }
            return convertedList;
        }
        return null;
    }

    @Override
    public Map<String, Object> viewExam_By_ExamID(Long examID, Long courseID) throws JpaSystemException {
        List<Map<String, Object>> queryList = getExamsByCourseID(courseID);
        Optional<Map<String, Object>> exam = queryList.stream()
                .filter(queryMap -> examID.equals(queryMap.get("examID")))
                .findFirst();
        return exam.orElse(null);
    }

    @Override
    @Transactional
    public Long deleteExam_By_ExamID(Long examID) throws JpaSystemException {
        Exam exam = examRepository.findExamByExamID(examID);
        if(exam != null) {
            entityManager.createNativeQuery("delete from question_java_core_exam where exam_id = :examID")
                    .setParameter("examID", exam.getExamID())
                    .executeUpdate();

            entityManager.createNativeQuery("delete from mark_student where exam_id = :examID")
                    .setParameter("examID", exam.getExamID())
                    .executeUpdate();

            entityManager.createNativeQuery("delete from exam where exam_id = :examID")
                    .setParameter("examID", exam.getExamID())
                    .executeUpdate();
            return exam.getExamID();
        }
        return null;
    }

    @Override
    public Exam findExam_By_ExamID(Long examID) {
        return examRepository.findExamByExamID(examID);
    }

    @Override
    @Transactional
    public Exam updateExam_By_ExamID(Exam exam, Long examID) throws JpaSystemException{
        Exam examFound = findExam_By_ExamID(examID);
        if(examFound != null) {
            String sqlUpdate = "update exam \n" +
                    "set \n" +
                    "    title_exam = ?, \n" +
                    "    type_exam = ?, \n" +
                    "    topic_exam = ?, \n" +
                    "    retake_exam = ?, \n" +
                    "    scoring_method = ?, \n" +
                    "    duration = ?, \n" +
                    "    start_date = ?, \n" +
                    "    end_date = ?, \n" +
                    "    link_exam_paper = ?, \n" +
                    "    password_exam = ?\n" +
                    "where \n" +
                    "    exam_id = ?;";

            entityManager.createNativeQuery(sqlUpdate)
                    .setParameter(1, exam.getTitleExam())
                    .setParameter(2, exam.getTypeExam())
                    .setParameter(3, exam.getTopicExam())
                    .setParameter(4, exam.isRetakeExam())
                    .setParameter(5, exam.getScoringMethod())
                    .setParameter(6, exam.getDuration())
                    .setParameter(7, exam.getStartDate())
                    .setParameter(8, exam.getEndDate())
                    .setParameter(9, exam.getLinkExamPaper())
                    .setParameter(10, exam.getPasswordExam())
                    .setParameter(11, exam.getExamID())
                    .executeUpdate();
            return examFound;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getExams_Calendar_Lecture_By_StartDate(String lectureID,
                                                                            int yearStartDate,
                                                                            int monthStartDate,
                                                                            int dateStartDate) throws JpaSystemException {
        String yearStartDateString = Integer.toString(yearStartDate);
        String monthStartDateString = Integer.toString(monthStartDate);
        String dateStartDateString = Integer.toString(dateStartDate);
        String dateFormat = yearStartDateString + "-" + monthStartDateString + "-" + dateStartDateString;
        return examRepository.getExams_Calendar_Lecture_By_StartDate(lectureID,dateFormat).stream()
                .map(originalMap -> {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("examID", originalMap.get("exam_id"));
                    newMap.put("titleExam", originalMap.get("title_exam"));
                    newMap.put("typeExam", originalMap.get("type_exam"));
                    newMap.put("className", originalMap.get("class_name"));
                    return newMap;
                })
                .collect(Collectors.toList());
    }


}

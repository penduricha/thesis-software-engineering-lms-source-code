package com.example.backend_service.services;

import com.example.backend_service.models.BankQuestionJavaCore;
import com.example.backend_service.models.Course;
import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.services.i_service.I_ExamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExamService implements I_ExamService {

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
    public Map<String, Object> getExamsByCourseID(Long courseID) {
        return Map.of();
    }
}

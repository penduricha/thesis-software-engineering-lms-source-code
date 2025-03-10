package com.example.backend_service.services;

import com.example.backend_service.models.Exam;
import com.example.backend_service.models.QuestionJavaCoreExam;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.QuestionJavaCoreExamRepository;
import com.example.backend_service.services.i_service.I_QuestionJavaCoreExamService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionJavaCoreExamService implements I_QuestionJavaCoreExamService {
    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    private final ExamRepository examRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public QuestionJavaCoreExamService(QuestionJavaCoreExamRepository questionJavaCoreExamRepository, ExamRepository examRepository) {
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
        this.examRepository = examRepository;
    }


    @Override
    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams_By_ExamID(Long examID) throws JpaSystemException{
        // Retrieve the list from the repository
        List<QuestionJavaCoreExam> questions = questionJavaCoreExamRepository.findQuestionJavaCoreExamsByExam_ExamID(examID);
        // Shuffle the list
        if (questions != null) {
            Collections.shuffle(questions);
            return questions;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getQuestionJavaCoreExam_By_ExamID_To_Student_Exam(Long examID)
            throws JpaSystemException {
        List<Map<String, Object>> questionsQuery =
                questionJavaCoreExamRepository.findQuestionJavaCoreExamsByExam_ExamID_Map(examID);
        if(questionsQuery != null) {
            //Collections.shuffle(questionsQuery);
            //List<Map<String, Object>> questionsReturn
            return questionsQuery.stream()
                    .map(originalMap -> {
                        Map<String, Object> newMap = new HashMap<>();
                        newMap.put("questionJavaCoreExamID", originalMap.get("question_java_core_exam_id"));
                        newMap.put("codeSample", originalMap.get("code_sample"));
                        newMap.put("contentQuestion", originalMap.get("content_question"));
                        newMap.put("score", originalMap.get("score"));
                        newMap.put("questionJavaCoreID", originalMap.get("question_java_core_id"));
                        return newMap;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    @Transactional
    public Exam updateQuestionJavaCoreExams_By_ExamID
            (Long examID, List<QuestionJavaCoreExam> questionJavaCoreExams)
            throws JpaSystemException {
        Exam examFound = examRepository.findExamByExamID(examID);
        if(examFound != null) {
            //delete all
            String sqlDeleteAl_By_ExamID = "delete from question_java_core_exam where exam_id = ?";
            entityManager.createNativeQuery(sqlDeleteAl_By_ExamID)
                    .setParameter(1, examFound.getExamID())
                    .executeUpdate();

            if(!questionJavaCoreExams.isEmpty()) {
                for(QuestionJavaCoreExam questionJavaCoreExam: questionJavaCoreExams) {
                    String sqlInsert =
                    "insert into question_java_core_exam (code_sample, content_question, question_java_core_id, exam_id, score) " +
                    "values (?, ?, ? ,? ,?)";
                    entityManager.createNativeQuery(sqlInsert)
                            .setParameter(1, questionJavaCoreExam.getCodeSample())
                            .setParameter(2, questionJavaCoreExam.getContentQuestion())
                            .setParameter(3, questionJavaCoreExam.getBankQuestionJavaCore().getQuestionJavaCoreID())
                            .setParameter(4, examFound.getExamID())
                            .setParameter(5, questionJavaCoreExam.getScore())
                            .executeUpdate();
                }
                return examFound;
            }
        }
        return null;
    }


}

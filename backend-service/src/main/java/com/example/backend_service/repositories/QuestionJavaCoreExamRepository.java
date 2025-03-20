package com.example.backend_service.repositories;

import com.example.backend_service.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface QuestionJavaCoreExamRepository extends JpaRepository<QuestionJavaCoreExam,Long> {

    @Query(value = "select count(*) from question_java_core_exam where exam_id = :examID", nativeQuery = true)
    Integer getTotalQuestionsByExamID(@Param("examID") Long examID);

    List<QuestionJavaCoreExam> findQuestionJavaCoreExamsByExam_ExamID(Long examID);

    @Query(value = """
                    select question_java_core_exam_id,
                           code_sample,
                           content_question,
                            score,
                        is_marked_flag,
                        question_java_core_id
                    from question_java_core_exam where exam_id = :examID;
                    """, nativeQuery = true)
    List<Map<String, Object>>  findQuestionJavaCoreExamsByExam_ExamID_Map(@Param("examID") Long examID);

//    @Query(value = """
//                    select q.content_question,
//                           btcjc.code_run_to_output,
//                           q.score
//                    from question_java_core_exam q
//                    right join bank_question_java_core btcjc
//                    on q.question_java_core_id = btcjc.question_java_core_id
//                    where q.question_java_core_exam_id = :questionJavaCoreExamID;
//                    """, nativeQuery = true)
//    Map<String, Object>  findQuestionJavaCoreExams_Content_Question_CodeRunToOutput_Score_By_QuestionJavaCoreExamID
//            (@Param("questionJavaCoreExamID") Long questionJavaCoreExamID);

    void deleteAllByExam_ExamID(Long examID);

    QuestionJavaCoreExam findQuestionJavaCoreExamsByQuestionJavaCoreExamID(Long questionJavaCoreExamID);
}

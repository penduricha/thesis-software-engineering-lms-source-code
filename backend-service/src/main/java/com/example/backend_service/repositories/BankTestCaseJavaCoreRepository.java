package com.example.backend_service.repositories;


import com.example.backend_service.models.BankTestCaseJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface BankTestCaseJavaCoreRepository extends JpaRepository<BankTestCaseJavaCore,Long> {

    List<BankTestCaseJavaCore> getBankTestCaseJavaCoresByBankQuestionJavaCore_QuestionJavaCoreID(Long questionJavaCoreID);

    @Query(value = """
                    select input_test, output_expect, note from bank_test_case_java_core btc
                    right join bank_question_java_core bq on btc.question_java_core_id = bq.question_java_core_id
                    left join question_java_core_exam q on bq.question_java_core_id = q.question_java_core_id
                    where q.question_java_core_exam_id = :questionJavaCoreExamID;
                    """, nativeQuery = true)
    List<Map<String, Object>> getTestCases_By_QuestionJavaCoreExamID(@Param("questionJavaCoreExamID") Long questionJavaCoreExamID);
}

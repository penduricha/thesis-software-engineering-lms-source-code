package com.example.backend_service.repositories;

import com.example.backend_service.models.BankQuestionJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BankQuestionJavaCoreRepository extends JpaRepository<BankQuestionJavaCore,Long> {

    @Query(value = """
            select b.question_java_core_id, b.code_sample, b.content_question
            from bank_question_java_core b order by rand() limit 10;
             """,
            nativeQuery = true)
    List<Map<String, Object>> getRandom_10_Questions_JavaCore();

    BankQuestionJavaCore findBankQuestionJavaCoreByQuestionJavaCoreID(Long questionJavaCoreID);

    @Query(value = """
            select count(*) from bank_question_java_core;
            """,
            nativeQuery = true)
    Long getTotalBankQuestionJavaCore();

    @Query(value = """
            select b.question_java_core_id, b.code_sample, b.content_question
            from bank_question_java_core b order by rand() limit :numberOfQuestions;
             """,
            nativeQuery = true)
    List<Map<String, Object>> getRandom_Questions_JavaCore_By_NumberQuestions
            (@Param("numberOfQuestions") int numberOfQuestions);
}

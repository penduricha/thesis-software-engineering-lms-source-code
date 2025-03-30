package com.example.backend_service.repositories;

import com.example.backend_service.models.ResultQuestionJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ResultQuestionJavaCoreRepository extends JpaRepository<ResultQuestionJavaCore, Long> {
    @Query(value = """
            select r.result_question_java_core_id, r.code_student_submitted, bqjc.code_run_to_output
            from result_question_java_core r
            left join question_java_core_exam qjce
            on r.question_java_core_exam_id = qjce.question_java_core_exam_id
            left join bank_question_java_core bqjc
            on qjce.question_java_core_id = bqjc.question_java_core_id
            where detail_mark_student_id = :detailMarkStudentID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> get_Code_Submitted_And_Code_Run_To_Output
            (@Param("detailMarkStudentID") Long detailMarkStudentID);

    @Query(value = """
            select count(*) from result_question_java_core r
            left join question_java_core_exam qjce
            on r.question_java_core_exam_id = qjce.question_java_core_exam_id
            left join bank_question_java_core bqjc
            on qjce.question_java_core_id = bqjc.question_java_core_id
            left join bank_test_case_java_core btcjc
            on bqjc.question_java_core_id = btcjc.question_java_core_id
            where result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    int getSizeOfTestCases_JavaCore_By_Result_Question_JavaCore_ID
            (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);

    ResultQuestionJavaCore findResultQuestionJavaCoreByResultQuestionJavaCoreID(Long resultQuestionJavaCoreID);

    @Query(value = """
           select btcjc.output_expect from result_question_java_core rqjc
           right join question_java_core_exam qjce
           on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
           right join bank_question_java_core bqjc
           on qjce.question_java_core_id = bqjc.question_java_core_id
           left join bank_test_case_java_core btcjc
           on bqjc.question_java_core_id = btcjc.question_java_core_id
           where result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    List<String> getTestCasesByResultQuestionJavaCoreID(@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);

    @Query(value = """
            select o.output_debug_java_core_id, o.output_code_student
           from output_debug_result_java_core o
           where result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getOutputCode_By_ResultQuestionJavaCoreID
            (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);

    List<ResultQuestionJavaCore> getResultQuestionJavaCoresByDetailMarkStudent_DetailMarkStudentID(Long detailMarkStudentID);

    @Query(value = """
            select q.score from question_java_core_exam q
            left join result_question_java_core r
            on q.question_java_core_exam_id = r.question_java_core_exam_id
            where result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    Double getScoreQuestionJavaCoreExamBy_Result_Question_JavaCore_ID
            (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);

    @Query(value = """
            select sum(r.mark_achieve) 
            from result_question_java_core r
            where detail_mark_student_id = :detailMarkStudentID;
            """,
            nativeQuery = true)
    Double getTotal_MarkAchieve_By_Detail_MarkStudent(@Param("detailMarkStudentID") Long detailMarkStudentID);

//    @Query(value = """
//            with first_query as (
//                select btcjc.input_test, btcjc.output_expect
//                from result_question_java_core rqjc
//                         right join question_java_core_exam qjce
//                                    on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
//                         right join bank_question_java_core bqjc
//                                    on qjce.question_java_core_id = bqjc.question_java_core_id
//                         left join bank_test_case_java_core btcjc
//                                   on bqjc.question_java_core_id = btcjc.question_java_core_id
//                where rqjc.result_question_java_core_id = :resultQuestionJavaCoreID
//            ),
//                 second_query as (
//                     select o.output_debug_java_core_id, o.output_code_student, o.fail
//                     from output_debug_result_java_core o
//                     where o.result_question_java_core_id = :resultQuestionJavaCoreID
//                 )
//            select fq.input_test, fq.output_expect, sq.output_code_student, sq.fail
//            from first_query fq
//            join second_query sq
//            on length(fq.output_expect) = length(sq.output_code_student);
//            """,
//            nativeQuery = true)
//    List<Map<String, Object>> getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID
//            (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);


    @Query(value = """
            select r.result_question_java_core_id, q.content_question, r.code_student_submitted,
                   r.mark_achieve, q.score
            from result_question_java_core r
            right join question_java_core_exam q
            on r.question_java_core_exam_id = q.question_java_core_exam_id
            where detail_mark_student_id = :detailMarkStudentID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getListQuestionJavaCoreExamAndResult_By_Detail_MarkStudent_ID
            (@Param("detailMarkStudentID") Long detailMarkStudentID);

    /*
    select btcjc.input_test, btcjc.output_expect
from result_question_java_core rqjc
             right join question_java_core_exam qjce
                        on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
             right join bank_question_java_core bqjc
                        on qjce.question_java_core_id = bqjc.question_java_core_id
             left join bank_test_case_java_core btcjc
                       on bqjc.question_java_core_id = btcjc.question_java_core_id
where rqjc.result_question_java_core_id = :resultQuestionJavaCoreID;

select o.output_debug_java_core_id, o.output_code_student, o.fail
from output_debug_result_java_core o
where o.result_question_java_core_id = :resultQuestionJavaCoreID;
     */
    @Query(value = """
            select btcjc.input_test, btcjc.output_expect
            from result_question_java_core rqjc
                         right join question_java_core_exam qjce
                                    on rqjc.question_java_core_exam_id = qjce.question_java_core_exam_id
                         right join bank_question_java_core bqjc
                                    on qjce.question_java_core_id = bqjc.question_java_core_id
                         left join bank_test_case_java_core btcjc
                                   on bqjc.question_java_core_id = btcjc.question_java_core_id
            where rqjc.result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getListTestCase_By_Result_Question_JavaCore_ID
    (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);

    @Query(value = """
            select o.output_debug_java_core_id, o.output_code_student, o.fail
            from output_debug_result_java_core o
            where o.result_question_java_core_id = :resultQuestionJavaCoreID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getOutputTestCase_By_Result_Question_JavaCore_ID
            (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);
}

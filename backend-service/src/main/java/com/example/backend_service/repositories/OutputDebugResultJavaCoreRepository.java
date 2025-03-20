package com.example.backend_service.repositories;

import com.example.backend_service.models.OutputDebugResultJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface OutputDebugResultJavaCoreRepository extends JpaRepository<OutputDebugResultJavaCore, Long> {
    //find by ID
    public OutputDebugResultJavaCore findOutputDebugResultJavaCoreByOutputDebugJavaCoreID(Long outputDebugJavaCoreID);

//    public List<OutputDebugResultJavaCore>
//            findOutputDebugResultJavaCoresByResultQuestionJavaCore_ResultQuestionJavaCoreID (Long resultQuestionJavaCoreID);
    @Query(value = """
                select o.fail
                from output_debug_result_java_core o
                where result_question_java_core_id = :resultQuestionJavaCoreID;
                """,
            nativeQuery = true)
    public List<Boolean> get_List_Status_TestCases_By_ResultQuestionJavaCoreID
    (@Param("resultQuestionJavaCoreID") Long resultQuestionJavaCoreID);
}

package com.example.backend_service.services;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.ResultQuestionJavaCore;
import com.example.backend_service.repositories.DetailMarkStudentRepository;
import com.example.backend_service.repositories.ResultQuestionJavaCoreRepository;
import com.example.backend_service.services.i_service.I_ResultQuestionJavaCoreService;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultQuestionJavaCoreService {
    private final ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    public ResultQuestionJavaCoreService(ResultQuestionJavaCoreRepository resultQuestionJavaCoreRepository, DetailMarkStudentRepository detailMarkStudentRepository) {
        this.resultQuestionJavaCoreRepository = resultQuestionJavaCoreRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
    }

    public List<Map<String, Object>> getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID(Long resultQuestionJavaCoreID)
        throws JpaSystemException {
        ResultQuestionJavaCore resultQuestionJavaCore = resultQuestionJavaCoreRepository
                .findResultQuestionJavaCoreByResultQuestionJavaCoreID(resultQuestionJavaCoreID);
        if(resultQuestionJavaCore != null) {
            List<Map<String, Object>> queryList = resultQuestionJavaCoreRepository
                    .getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID(resultQuestionJavaCoreID);
            if(!queryList.isEmpty()) {
                return queryList.stream()
                        .map(originalMap -> {
                            Map<String, Object> newMap = new HashMap<>();
                            newMap.put("inputTest", originalMap.get("input_test"));
                            newMap.put("outputExpect", originalMap.get("output_expect"));
                            newMap.put("outputCodeStudent", originalMap.get("output_code_student"));
                            newMap.put("fail", originalMap.get("fail"));
                            return newMap;
                        }).toList();

            }
        }
        return new ArrayList<>();
    }

    public List<Map<String, Object>> getListQuestionJavaCoreExamAndResult_By_Detail_MarkStudent_ID(Long detailMarkStudentID)
            throws JpaSystemException{
        DetailMarkStudent detailMarkStudent =
                detailMarkStudentRepository.findDetailMarkStudentByDetailMarkStudentID(detailMarkStudentID);
        if(detailMarkStudent != null) {
            List<Map<String, Object>> queryList = resultQuestionJavaCoreRepository.
                    getListQuestionJavaCoreExamAndResult_By_Detail_MarkStudent_ID(detailMarkStudentID);
            if(!queryList.isEmpty()) {
                return queryList.stream()
                        .map(originalMap -> {
                            Map<String, Object> newMap = new HashMap<>();
                            newMap.put("resultQuestionJavaCoreID", originalMap.get("result_question_java_core_id"));
                            newMap.put("contentQuestion", originalMap.get("content_question"));
                            newMap.put("markAchieve", originalMap.get("mark_achieve"));
                            newMap.put("score", originalMap.get("score"));
                            return newMap;
                        }).toList();

            }
        }
        return new ArrayList<>();

    }
}

package com.example.backend_service.services.i_service;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface I_ResultQuestionJavaCoreService {
    public List<String> getTestCasesByResultQuestionJavaCoreID(Long resultQuestionJavaCoreID);

    public List<Map<String, Object>> getOutputCode_By_ResultQuestionJavaCoreID
            (Long resultQuestionJavaCoreID);

    public List<Long> getList_Output_Debug_JavaCoreID_To_Update_Pass (Long resultQuestionJavaCoreID) throws Exception;

    public List<Map<String, Object>> getListTestCase_And_OutputStudent_By_Result_Question_JavaCore_ID
            (Long resultQuestionJavaCoreID);
}

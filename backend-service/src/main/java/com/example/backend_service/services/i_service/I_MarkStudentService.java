package com.example.backend_service.services.i_service;

import com.example.backend_service.dto.CodeSubmitAndCodeMain;
import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.models.ResultQuestionJavaCore;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface I_MarkStudentService {
    //public MarkStudent createMarkStudent(Map<String, Object> dataSubmitPost);

    public DetailMarkStudent createMarkStudent(Map<String, Object> dataSubmitPost);

    //public DetailMarkStudent createDetailMarkStudentIfMarkStudentExist(Map<String, Object> dataSubmitPost);
    
    public DetailMarkStudent insertOutput_From_JavaCore_Code_Submitted(Map<String, Object> dataSubmitPost);

    public DetailMarkStudent update_Fail_To_False_If_Same_Output(Map<String, Object> dataSubmitPost);

    public DetailMarkStudent setMarkAchieve_After_Output(Map<String, Object> dataSubmitPost);

    public DetailMarkStudent setDetailMarkStudent_After_SetMarkAchieve(Map<String, Object> dataSubmitPost);

    public MarkStudent setMarkExam_After_SetDetailMarkExam(Map<String, Object> dataSubmitPost);

    public MarkStudent setMarkIs_Zero_If_Exam_Overdue (String studentID, Long examID);

    //public List<Map<String, Object>> get_Code_Submitted_And_Code_Run_To_Output(Long detailMarkStudentID);
    public List<CodeSubmitAndCodeMain> get_Code_Submitted_And_Code_Run_To_Output(Long detailMarkStudentID);

    public int getSizeOfTestCases_JavaCore_By_Result_Question_JavaCore_ID
            (Long resultQuestionJavaCoreID);

    public Void deleteMarkStudent_By_ExamID(Long examID);

    public List<Map<String, Object>> getListResultExam_By_StudentID (String studentID);

    public List<Map<String, Object>> getListDetailMarkStudent_By_MarkStudentID(Long markStudentID);
}

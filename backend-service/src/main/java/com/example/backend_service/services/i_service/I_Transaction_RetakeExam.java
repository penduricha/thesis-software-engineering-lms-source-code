package com.example.backend_service.services.i_service;
import java.util.*;
import com.example.backend_service.models.*;

public interface I_Transaction_RetakeExam {
    public DetailMarkStudent createDetailMarkStudentIfMarkStudentExist_RetakeExam(Map<String, Object> dataSubmitPost, MarkStudent markStudent);

    public DetailMarkStudent insertOutput_From_JavaCore_Code_Submitted_RetakeExam(Map<String, Object> dataSubmitPost, MarkStudent markStudent);

    public DetailMarkStudent update_Fail_To_False_If_Same_Output_RetakeExam(Map<String, Object> dataSubmitPost, MarkStudent markStudent);

    public DetailMarkStudent setMarkAchieve_After_Output_RetakeExam(Map<String, Object> dataSubmitPost, MarkStudent markStudent);

    public DetailMarkStudent setDetailMarkStudent_After_SetMarkAchieve_RetakeExam(Map<String, Object> dataSubmitPost, MarkStudent markStudent);

    public DetailMarkStudent setMarkExam_After_SetDetailMarkExam_RetakeExam
            (Map<String, Object> dataSubmitPost, MarkStudent markStudent, String scoringMethod);
}

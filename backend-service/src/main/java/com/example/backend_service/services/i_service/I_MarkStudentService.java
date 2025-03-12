package com.example.backend_service.services.i_service;

import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.models.ResultQuestionJavaCore;

import java.util.Map;

public interface I_MarkStudentService {
    public MarkStudent createMarkStudent(Map<String, Object> dataSubmitPost);

    public MarkStudent setMarkIs_Zero_If_Exam_Overdue (String studentID, Long examID);
}

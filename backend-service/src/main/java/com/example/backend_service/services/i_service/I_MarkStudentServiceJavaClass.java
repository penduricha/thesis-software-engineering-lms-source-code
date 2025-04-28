package com.example.backend_service.services.i_service;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;

import java.util.Map;

public interface I_MarkStudentServiceJavaClass {
    public DetailMarkStudent createMarkStudent_And_DetailMarkStudent(Map<String, Object> dataPut);

    public MarkStudent updateMarkStudent_Zero_If_Student_No_Submit_First (Map<String, Object> dataPut);

    public DetailMarkStudent updateMarkStudent_Zero_If_Student_No_Submit_Retake
            (Map<String, Object> dataPut, String scoringMethod, MarkStudent markStudentFound);

    public MarkStudent saveResultJavaClassFirst(Map<String, Object> dataPost);

    public DetailMarkStudent saveResultJavaClassRetake(Map<String, Object> dataPost, MarkStudent markStudentFound, String scoringMethod);
}

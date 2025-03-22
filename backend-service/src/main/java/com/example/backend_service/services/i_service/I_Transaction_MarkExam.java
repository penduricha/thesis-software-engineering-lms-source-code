package com.example.backend_service.services.i_service;
import java.util.*;

public interface I_Transaction_MarkExam {
    public List<Long> getListDetailMarkStudentID_By_MarkStudentID(Long markStudentID);

    public List<Long> getListResultQuestionJavaCoreID_By_MarkStudentID(Long markStudentID);

    public List<Long> getListOutputDebugResultJavaCoreID_By_MarkStudentID(Long markStudentID);

    public void deleteMarkStudentID_By_MarkStudentID(Long markStudentID);
}

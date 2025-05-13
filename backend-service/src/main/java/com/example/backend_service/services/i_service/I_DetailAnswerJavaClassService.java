package com.example.backend_service.services.i_service;

import com.example.backend_service.models.DetailAnswerJavaClass;
import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.services.DetailAnswerJavaClassService;
import java.util.*;

public interface I_DetailAnswerJavaClassService {
    public DetailAnswerJavaClass saveDetailAnswerJavaClass(DetailAnswerJavaClass detailAnswerJavaClass);

    public List<DetailAnswerJavaClass> getListDetailAnswerJavaClass_By_DetailMarkStudentID(Long detailMarkStudentID);


}

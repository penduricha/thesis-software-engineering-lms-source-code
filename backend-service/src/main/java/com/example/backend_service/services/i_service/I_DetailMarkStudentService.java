package com.example.backend_service.services.i_service;

import com.example.backend_service.models.DetailMarkStudent;

import java.util.List;

public interface I_DetailMarkStudentService {
    public List<DetailMarkStudent> getDetailMarkStudentsByMarkStudent_MarkStudentID(Long markStudentID);
}

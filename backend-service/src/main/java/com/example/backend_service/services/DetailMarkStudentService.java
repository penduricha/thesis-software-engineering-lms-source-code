package com.example.backend_service.services;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import com.example.backend_service.repositories.DetailMarkStudentRepository;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.services.i_service.I_DetailMarkStudentService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailMarkStudentService implements I_DetailMarkStudentService {
    private final MarkStudentRepository markStudentRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    public DetailMarkStudentService(MarkStudentRepository markStudentRepository, DetailMarkStudentRepository detailMarkStudentRepository) {
        this.markStudentRepository = markStudentRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
    }

    @Override
    public List<DetailMarkStudent> getDetailMarkStudentsByMarkStudent_MarkStudentID(Long markStudentID)
            throws JpaSystemException {
        MarkStudent markStudent = markStudentRepository.findMarkStudentByMarkStudentID(markStudentID);
        if(markStudent != null) {
            return detailMarkStudentRepository
                    .getDetailMarkStudentsByMarkStudent_MarkStudentID(markStudent.getMarkStudentID());
        }
        return new ArrayList<>();
    }
}

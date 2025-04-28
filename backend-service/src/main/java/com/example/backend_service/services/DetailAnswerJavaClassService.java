package com.example.backend_service.services;

import com.example.backend_service.models.DetailAnswerJavaClass;
import com.example.backend_service.repositories.DetailAnswerJavaClassRepository;
import com.example.backend_service.services.i_service.I_DetailAnswerJavaClassService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailAnswerJavaClassService implements I_DetailAnswerJavaClassService {
    private final DetailAnswerJavaClassRepository detailAnswerJavaClassRepository;

    public DetailAnswerJavaClassService(DetailAnswerJavaClassRepository detailAnswerJavaClassRepository) {
        this.detailAnswerJavaClassRepository = detailAnswerJavaClassRepository;
    }

    @Override
    public DetailAnswerJavaClass saveDetailAnswerJavaClass(DetailAnswerJavaClass detailAnswerJavaClass)
            throws JpaSystemException {
        return detailAnswerJavaClassRepository.save(detailAnswerJavaClass);
    }

    @Override
    public List<DetailAnswerJavaClass> getListDetailAnswerJavaClass_By_DetailMarkStudentID(Long detailMarkStudentID)
            throws JpaSystemException {
        return List.of();
    }
}

package com.example.backend_service.services;

import com.example.backend_service.models.DetailAnswerJavaClass;
import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.repositories.DetailAnswerJavaClassRepository;
import com.example.backend_service.repositories.DetailMarkStudentRepository;
import com.example.backend_service.services.i_service.I_DetailAnswerJavaClassService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailAnswerJavaClassService implements I_DetailAnswerJavaClassService {
    private final DetailAnswerJavaClassRepository detailAnswerJavaClassRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    public DetailAnswerJavaClassService(DetailAnswerJavaClassRepository detailAnswerJavaClassRepository, DetailMarkStudentRepository detailMarkStudentRepository) {
        this.detailAnswerJavaClassRepository = detailAnswerJavaClassRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
    }

    @Override
    public DetailAnswerJavaClass saveDetailAnswerJavaClass(DetailAnswerJavaClass detailAnswerJavaClass)
            throws JpaSystemException {
        return detailAnswerJavaClassRepository.save(detailAnswerJavaClass);
    }

    @Override
    public List<DetailAnswerJavaClass> getListDetailAnswerJavaClass_By_DetailMarkStudentID(Long detailMarkStudentID)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudentFound = detailMarkStudentRepository.findDetailMarkStudentByDetailMarkStudentID(detailMarkStudentID);
        if(detailMarkStudentFound != null) {
            return detailAnswerJavaClassRepository
                    .findDetailAnswerJavaClassesByDetailMarkStudent_DetailMarkStudentID
                            (detailMarkStudentFound.getDetailMarkStudentID());
        }
        return new ArrayList<>();
    }
}

package com.example.backend_service.services;

import com.example.backend_service.models.ExamJavaOop;
import com.example.backend_service.repositories.ExamJavaOopRepository;
import com.example.backend_service.services.i_service.I_ExamOopJavaService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ExamJavaOopService implements I_ExamOopJavaService {
    private final ExamJavaOopRepository examJavaOopRepository;

    public ExamJavaOopService(ExamJavaOopRepository examJavaOopRepository) {
        this.examJavaOopRepository = examJavaOopRepository;
    }

    @Override
    public List<ExamJavaOop> getAllExamJavaOop() {
        return examJavaOopRepository.findAll();
    }

    @Override
    public ExamJavaOop getExamJavaOopBy_BankTestJavaOopID(Long bankTestJavaOopID)
            throws JpaSystemException {
        return examJavaOopRepository.findExamJavaOopByBankTestJavaOop_BankTestJavaOopID(bankTestJavaOopID);
    }
}

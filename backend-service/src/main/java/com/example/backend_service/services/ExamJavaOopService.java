package com.example.backend_service.services;

import com.example.backend_service.models.BankTestJavaOop;
import com.example.backend_service.models.ExamJavaOop;
import com.example.backend_service.repositories.BankTestJavaOopRepository;
import com.example.backend_service.repositories.ExamJavaOopRepository;
import com.example.backend_service.services.i_service.I_ExamOopJavaService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamJavaOopService implements I_ExamOopJavaService {
    private final ExamJavaOopRepository examJavaOopRepository;

    private final BankTestJavaOopRepository bankTestJavaOopRepository;

    public ExamJavaOopService(ExamJavaOopRepository examJavaOopRepository, BankTestJavaOopRepository bankTestJavaOopRepository) {
        this.examJavaOopRepository = examJavaOopRepository;
        this.bankTestJavaOopRepository = bankTestJavaOopRepository;
    }

    @Override
    public List<ExamJavaOop> getAllExamJavaOop() {
        return examJavaOopRepository.findAll();
    }

    @Override
    public List<ExamJavaOop> getExamJavaOopBy_BankTestJavaOopID(Long bankTestJavaOopID)
            throws JpaSystemException {
        BankTestJavaOop bankTestJavaOop = bankTestJavaOopRepository.findBankTestJavaOopByBankTestJavaOopID(bankTestJavaOopID);
        if(bankTestJavaOop != null) {
            return examJavaOopRepository.findExamJavaOopsByBankTestJavaOop_BankTestJavaOopID(bankTestJavaOopID);
        }
        return null;
    }

    @Override
    public Boolean isExistExam(Long bankTestJavaOopID) {
        List<ExamJavaOop> examJavaOops = getExamJavaOopBy_BankTestJavaOopID(bankTestJavaOopID);
        return (!examJavaOops.isEmpty());
    }
}

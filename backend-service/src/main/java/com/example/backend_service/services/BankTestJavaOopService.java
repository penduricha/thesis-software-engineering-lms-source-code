package com.example.backend_service.services;

import com.example.backend_service.models.BankTestJavaOop;
import com.example.backend_service.models.*;
import com.example.backend_service.repositories.BankTestJavaOopRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.services.i_service.I_BankTestJavaOopService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class BankTestJavaOopService implements I_BankTestJavaOopService {
    @PersistenceContext
    private EntityManager entityManager;

    private final BankTestJavaOopRepository bankTestJavaOopRepository;

    private final ExamJavaOopService examJavaOopService;

    private final ExamRepository examRepository;

    private final StudentService studentService;

    public BankTestJavaOopService(BankTestJavaOopRepository bankTestJavaOopRepository, ExamJavaOopService examJavaOopService, ExamRepository examRepository, StudentService studentService) {
        this.bankTestJavaOopRepository = bankTestJavaOopRepository;
        this.examJavaOopService = examJavaOopService;
        this.examRepository = examRepository;
        this.studentService = studentService;
    }

    @Override
    public BankTestJavaOop addBankTestJavaOop(BankTestJavaOop bankTestJavaOop) throws JpaSystemException {
        return bankTestJavaOopRepository.save(bankTestJavaOop);
    }

    @Override
    public List<BankTestJavaOop> getAllBankTestJavaOop() throws JpaSystemException {
        return bankTestJavaOopRepository.findAll();
    }

    @Override
    @Transactional
    public Long deleteBankTestJavaOop(Long bankTestJavaOopID) throws JpaSystemException {
        //xet them record lien quan nua;
        BankTestJavaOop bankTestJavaOop = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        System.out.println(bankTestJavaOopID);
        if(bankTestJavaOop != null) {
            List<ExamJavaOop> examJavaOopFounds = examJavaOopService
                    .getExamJavaOopBy_BankTestJavaOopID(bankTestJavaOopID);
            if(examJavaOopFounds.isEmpty()) {
                //ktra delete them exam java class
                String sqlDeleteBankTestOop = "delete from bank_test_java_oop where bank_test_java_oop_id = ?";
                entityManager.createNativeQuery(sqlDeleteBankTestOop)
                        .setParameter(1, bankTestJavaOopID)
                        .executeUpdate();
                return bankTestJavaOopID;
            }
        }
        return null;
    }

    @Override
    public BankTestJavaOop findBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID) throws JpaSystemException {
        return bankTestJavaOopRepository.findBankTestJavaOopByBankTestJavaOopID(bankTestJavaOopID);
    }

    @Override
    public BankTestJavaOop updateBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID, BankTestJavaOop bankTestJavaOop)
            throws JpaSystemException {
        BankTestJavaOop bankTestJavaOopFound = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        if(bankTestJavaOopFound != null) {
            bankTestJavaOopFound.setNameTest(bankTestJavaOop.getNameTest());
            bankTestJavaOopFound.setDescriptionTest(bankTestJavaOop.getDescriptionTest());
            bankTestJavaOopFound.setImageDiagram(bankTestJavaOop.getImageDiagram());
            return bankTestJavaOopRepository.save(bankTestJavaOopFound);
        }
        return null;
    }

    @Override
    public Long getBankTestJavOopID_By_ExamID(Long examID) throws JpaSystemException{
        Exam examFound = examRepository.findExamByExamID(examID);
        if(examFound != null) {
            Long bankTestJavaOopID = bankTestJavaOopRepository
                    .getBankTestJavaOopBy_ExamID(examID);
            BankTestJavaOop bankTestJavaOopFound = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
            if(bankTestJavaOopFound != null) {
                return bankTestJavaOopFound.getBankTestJavaOopID();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Exam updateBankTestJavaOop_To_Exam_By_ExamID(Long examID, Long bankTestJavaOopID) throws JpaSystemException{
        Exam examFound = examRepository.findExamByExamID(examID);
        BankTestJavaOop bankTestJavaOopFound = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        if(examFound != null && bankTestJavaOopFound != null) {
            String sqlUpdate = "update exam_java_oop set bank_test_java_oop_id = ? where exam_id = ?";
            entityManager.createNativeQuery(sqlUpdate)
                    .setParameter(1, bankTestJavaOopFound.getBankTestJavaOopID())
                    .setParameter(2, examFound.getExamID())
                    .executeUpdate();
            return examFound;
        }
        return null;
    }

    @Override
    public BankTestJavaOop getBankTestJavaOop_By_ExamID(Long examID) throws JpaSystemException{
        Exam examFound = examRepository.findExamByExamID(examID);
        if(examFound != null) {
            Long bankTestJavaOopID = bankTestJavaOopRepository
                    .getBankTestJavaOopBy_ExamID(examID);
            return findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        }
        return null;
    }

    @Override
    public List<Long> getListExamIDByBankTestJavaOopID(Long bankTestJavaOopID) throws JpaSystemException{
        BankTestJavaOop bankTestJavaOopFound = findBankTestJavaOop_By_BankTestJavaOopID(bankTestJavaOopID);
        if(bankTestJavaOopFound != null) {
            return bankTestJavaOopRepository.getListExamIDByBankTestJavaOopID(bankTestJavaOopID);
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean getStatus_Student_DoExam_By_BankTestJavaOopID(Long bankTestJavaOopID) throws JpaSystemException{
        List<Long> listExamIDByBankTestJavaOopID = getListExamIDByBankTestJavaOopID(bankTestJavaOopID);
        if(!listExamIDByBankTestJavaOopID.isEmpty()) {
            for(Long examID: listExamIDByBankTestJavaOopID) {
                boolean studentAccessExam =
                        !Objects.equals(studentService.findStudent_Access_Exam_By_ExamID(examID), new HashMap<>());
                if(studentAccessExam) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Boolean studentAccessExam =
                !Objects.equals(studentService.findStudent_Access_Exam_By_ExamID(examID), new HashMap<>())
     */
}

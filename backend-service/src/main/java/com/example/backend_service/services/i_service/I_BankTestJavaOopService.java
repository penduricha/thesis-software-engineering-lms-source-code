package com.example.backend_service.services.i_service;

import com.example.backend_service.models.BankTestJavaOop;
import com.example.backend_service.models.Exam;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface I_BankTestJavaOopService {
    public BankTestJavaOop addBankTestJavaOop(BankTestJavaOop bankTestJavaOop);

    public List<BankTestJavaOop> getAllBankTestJavaOop();

    public Long deleteBankTestJavaOop(Long bankTestJavaOopID);

    public BankTestJavaOop findBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID);

    public BankTestJavaOop updateBankTestJavaOop_By_BankTestJavaOopID(Long bankTestJavaOopID, BankTestJavaOop bankTestJavaOop);

    public Long getBankTestJavOopID_By_ExamID(Long examID);

    public Exam updateBankTestJavaOop_To_Exam_By_ExamID(Long examID, Long bankTestJavaOopID);

    public BankTestJavaOop getBankTestJavaOop_By_ExamID(Long examID);

    public List<Long> getListExamIDByBankTestJavaOopID(Long bankTestJavaOopID);

    public Boolean getStatus_Student_DoExam_By_BankTestJavaOopID(Long bankTestJavaOopID);
}

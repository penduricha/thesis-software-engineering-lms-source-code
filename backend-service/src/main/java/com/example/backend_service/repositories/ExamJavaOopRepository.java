package com.example.backend_service.repositories;

import com.example.backend_service.models.ExamJavaOop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamJavaOopRepository extends JpaRepository<ExamJavaOop, Long> {
    List<ExamJavaOop> findExamJavaOopsByBankTestJavaOop_BankTestJavaOopID(Long bankTestJavaOopID);
}

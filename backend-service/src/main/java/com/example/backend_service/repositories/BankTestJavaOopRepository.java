package com.example.backend_service.repositories;

import com.example.backend_service.models.BankTestJavaOop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTestJavaOopRepository extends JpaRepository<BankTestJavaOop, Long> {
   //findAll(Sort.by(Sort.Direction.ASC, "id"));
    BankTestJavaOop findBankTestJavaOopByBankTestJavaOopID(Long bankTestJavaOopID);
}

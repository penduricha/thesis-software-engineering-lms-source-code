package com.example.backend_service.repositories;


import com.example.backend_service.models.BankTestCaseJavaCore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTestCaseRepository extends JpaRepository<BankTestCaseJavaCore,Long> {

}

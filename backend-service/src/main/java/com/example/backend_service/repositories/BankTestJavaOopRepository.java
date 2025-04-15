package com.example.backend_service.repositories;

import com.example.backend_service.models.BankTestJavaOop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankTestJavaOopRepository extends JpaRepository<BankTestJavaOop, Long> {
   //findAll(Sort.by(Sort.Direction.ASC, "id"));
    BankTestJavaOop findBankTestJavaOopByBankTestJavaOopID(Long bankTestJavaOopID);

    @Query(value = """
            
            """,
            nativeQuery = true)
    Long getBankTestJavaOopBy_ExamID(@Param("examID") Long examID);
}

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
            select btjo.bank_test_java_oop_id from exam e
            left join exam_java_oop ejo
            on e.exam_id = ejo.exam_id
            right join bank_test_java_oop btjo
            on ejo.bank_test_java_oop_id = btjo.bank_test_java_oop_id
            where e.exam_id = :examID;
            """,
            nativeQuery = true)
    Long getBankTestJavaOopBy_ExamID(@Param("examID") Long examID);

    @Query(value = """
            select b.bank_test_java_oop_id
            from bank_test_java_oop b order by rand() limit 1;
            """,
            nativeQuery = true)
    Long getRandomBankTestJavaOopID();

  @Query(value = """
            select exam_id from exam_java_oop where bank_test_java_oop_id = :bankTestJavaOopID;
            """,
         nativeQuery = true)
    List<Long> getListExamIDByBankTestJavaOopID(@Param("bankTestJavaOopID") Long bankTestJavaOopID);
}

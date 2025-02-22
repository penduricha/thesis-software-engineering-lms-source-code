package com.example.backend_service.repositories;

import com.example.backend_service.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {

}

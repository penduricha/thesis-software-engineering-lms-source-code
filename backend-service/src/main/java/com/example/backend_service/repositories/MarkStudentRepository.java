package com.example.backend_service.repositories;

import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkStudentRepository extends JpaRepository<MarkStudent, Long> {
    MarkStudent findMarkStudentByExam_ExamID(Long examID);
}

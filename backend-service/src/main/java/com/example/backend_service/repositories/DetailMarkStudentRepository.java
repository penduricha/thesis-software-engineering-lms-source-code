package com.example.backend_service.repositories;

import com.example.backend_service.models.DetailMarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailMarkStudentRepository extends JpaRepository<DetailMarkStudent, Long> {
    DetailMarkStudent findDetailMarkStudentByDetailMarkStudentID(Long detailMarkStudentID);
}

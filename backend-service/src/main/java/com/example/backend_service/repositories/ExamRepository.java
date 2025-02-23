package com.example.backend_service.repositories;

import com.example.backend_service.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface ExamRepository extends JpaRepository<Exam,Long> {

    @Query(value = "",
            nativeQuery = true)
    Map<String, Object> getExamsByCourseID(@Param("course_id") Long courseID);
}

package com.example.backend_service.repositories;


import com.example.backend_service.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByStudentID(String studentID);

//    @Query(value = "select s.student_id, " +
//            "s.password from Student s " +
//            "where s.student_id = :student_id",
//            nativeQuery = true
//    )
//    Map<String, Object> findStudentID_Password_ByStudentID(@Param("student_id") String studentID);

//    @Query(value = "SELECT s.student_id, s.password FROM Student s WHERE s.student_id = :student_id", nativeQuery = true)
//    List<StudentCredentialsDTO> findStudentID_Password_ByStudentID(@Param("student_id") String studentID);
}

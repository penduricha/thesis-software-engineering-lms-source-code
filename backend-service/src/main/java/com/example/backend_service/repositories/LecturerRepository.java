package com.example.backend_service.repositories;

import com.example.backend_service.models.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface LecturerRepository extends JpaRepository<Lecturer,Long> {

    Lecturer findLecturerByLecturerID(String lecturerID);

    @Query(value = """

            select l.lecture_id, email, name, phone_number from lecturer l
           where lecture_id = :lectureID;
           """,
            nativeQuery = true)
    Map<String, Object> findDetailInformation_By_LectureID(@Param("lecturerID") String lecturerID);
}

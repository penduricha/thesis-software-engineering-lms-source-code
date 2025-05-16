package com.example.backend_service.repositories;

import com.example.backend_service.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface LectureRepository extends JpaRepository<Lecture,Long> {

    Lecture findLectureByLectureID(String lectureID);

    @Query(value = """

            select l.lecture_id, email, name, phone_number from lecture l
           where lecture_id = :lectureID;
           """,
            nativeQuery = true)
    Map<String, Object> findDetailInformation_By_LectureID(@Param("lectureID") String lectureID);
}

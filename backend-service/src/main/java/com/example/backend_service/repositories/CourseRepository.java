package com.example.backend_service.repositories;

import com.example.backend_service.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<Course,Long> {


    @Query(value = "select c.course_id, c.class_name, c.course_name, c.semester, c.start_year, c.end_year " +
            "from Course c " +
            "left join Map_lecture_course mlc on c.course_id = mlc.course_id " +
            "left join Lecture l on mlc.lecture_id = l.lecture_id " +
            "where l.lecture_id = :lecture_id",
            nativeQuery = true)
    List<Map<String, Object>> getCoursesByLectureID(@Param("lecture_id") String lectureID);

    Course findCourseByCourseID(Long courseID);
}

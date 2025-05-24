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
            "left join map_lecturer_course mlc on c.course_id = mlc.course_id " +
            "left join lecturer l on mlc.lecturer_id = l.lecturer_id " +
            "where l.lecturer_id = :lecturer_id",
            nativeQuery = true)
    List<Map<String, Object>> getCoursesByLecturerID(@Param("lecturer_id") String lecturerID);

    Course findCourseByCourseID(Long courseID);

    @Query(value = """
        select course_id from exam where exam_id = :examID;
    """ , nativeQuery = true)
    Long findCourseIDByExamID(@Param("examID") Long examID);
}

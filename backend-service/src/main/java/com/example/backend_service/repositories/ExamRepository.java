package com.example.backend_service.repositories;

import com.example.backend_service.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ExamRepository extends JpaRepository<Exam,Long> {

    @Query(value = """
            select e.exam_id,
                   e.title_exam,
                   e.type_exam,
                   e.topic_exam,
                   e.retake_exam,
                   e.scoring_method, 
                   e.duration,            
                   e.start_date,
                   e.end_date,
                   e.link_exam_paper,
                   e.password_exam,
                   l.name
            from course c
                     left join map_lecture_course mlc on c.course_id = mlc.course_id
                     left join lecture l on mlc.lecture_id = l.lecture_id
                     left join project_thesis_final.exam e on c.course_id = e.course_id
            where e.course_id = :course_id;""",
            nativeQuery = true)
    List<Map<String, Object>> getExamsByCourseID(@Param("course_id") Long courseID);

    Exam findExamByExamID(Long examID);
}

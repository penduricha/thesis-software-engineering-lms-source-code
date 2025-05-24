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
                    e.view_table,
                   e.link_exam_paper,
                   e.password_exam,
                   l.name
            from course c
                     left join map_lecturer_course mlc on c.course_id = mlc.course_id
                     left join lecturer l on mlc.lecturer_id = l.lecturer_id
                     left join project_thesis_final.exam e on c.course_id = e.course_id
            where e.course_id = :course_id order by e.title_exam;""",
            nativeQuery = true)
    List<Map<String, Object>> getExamsByCourseID(@Param("course_id") Long courseID);

    Exam findExamByExamID(Long examID);

    @Query(value = """
            select
                e.exam_id,
                e.title_exam,
                e.type_exam,
                c.class_name
            from course c
                     left join map_lecturer_course mlc on c.course_id = mlc.course_id
                     left join lecturer l on mlc.lecturer_id = l.lecturer_id
                     right join exam e on c.course_id = e.course_id
            where l.lecturer_id = :lecturerID and date(start_date) = :startDate
            """,
            nativeQuery = true)
    List<Map<String, Object>> getExams_Calendar_Lecturer_By_StartDate(
            @Param("lecturerID") String lecturerId,
            @Param("startDate") String startDate);

    @Query(value = """
            select exam_id, retake_exam, scoring_method
            from exam where exam_id = :examID;
            """,
            nativeQuery = true)
    Map<String, Object> get_Status_Retake_And_Scoring_Method_By_ExamID(@Param("examID") Long examID);

    @Query(value = """
            select e.title_exam from exam e where 
            e.course_id = :courseID and e.title_exam = :titleExam;
            """,
            nativeQuery = true)
    String getTitle_Exam_By_CourseID(@Param("courseID") Long courseID,@Param("titleExam") String titleExam);

    //select * from mark_student where exam_id = 47;
    @Query(value = """
            select * from mark_student where student_id = :studentID and exam_id = :examID;
            """,
            nativeQuery = true)
    MarkStudent findMarkStudent_By_StudentID_ExamID
    (@Param("studentID") String studentID, @Param("examID") Long examID);

    List<Exam> getExamsByCourse_CourseID(Long courseID);
}

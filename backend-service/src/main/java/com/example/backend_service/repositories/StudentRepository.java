package com.example.backend_service.repositories;


import com.example.backend_service.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByStudentID(String studentID);

    @Query(value = """
            select
                e.exam_id,
                e.title_exam,
                e.type_exam,
                e.start_date, 
                e.end_date
            from student s
                right join course c on s.course_id = c.course_id
                right join exam e on c.course_id = e.course_id
            where student_id = :studentID and date(start_date) = :startDate;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getExams_Calendar_Student_By_StartDate(
            @Param("studentID") String studentID,
            @Param("startDate") String startDate);

    @Query(value = """
            select s.student_id, s.exam_id 
            from student_access_exam s
            where s.exam_id = :examID;
            """,
            nativeQuery = true)
    Map<String, Object> findStudent_Access_Exam_By_ExamID(@Param("examID") Long examID);

    @Query(value = """
            select
                sae.exam_id,
                (e.duration - timestampdiff(minute , s.date_time_start_exam, now()))
                as remain_minutes
            from student s
            left join
                student_access_exam sae on s.student_id = sae.student_id
            right join
                exam e on sae.exam_id = e.exam_id
            where s.student_id = :studentID;
            """,
            nativeQuery = true)
    Map<String, Object> get_Information_Student_Do_Exam(@Param("studentID") String studentID);

    @Query(value = """
            select s.student_id, date_of_birth, last_name, first_name, gender, c.course_name, c.class_name
            from student s
            left join course c on s.course_id = c.course_id
            where student_id = :studentID;
            """,
            nativeQuery = true)
    Map<String, Object> get_Information_Student_Detail_By_StudentID(@Param("studentID") String studentID);
}

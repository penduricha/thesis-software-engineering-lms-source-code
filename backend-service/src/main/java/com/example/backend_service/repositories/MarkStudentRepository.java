package com.example.backend_service.repositories;

import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarkStudentRepository extends JpaRepository<MarkStudent, Long> {
    MarkStudent findMarkStudentByExam_ExamID(Long examID);

    /*
        Truy xuất dbs sinh viên làm xong bài kiểm tra (exam) và đã có điểm ở (mark_student)
        ứng với bài kiểm tra đó. Mà bài kiểm tra đó có được phép làm lại
     */
    @Query(value = """
            select scoring_method from mark_student m
            left join exam e on m.exam_id = e.exam_id
            where e.retake_exam = true
            and m.student_id = :studentID and e.exam_id = :examID;
            """,
            nativeQuery = true)
    String getScoringMethod_If_Student_Had_MarkExam_And_Exam_Retake
    (@Param("studentID") String studentID, @Param("examID") Long examID);

    MarkStudent findMarkStudentByMarkStudentID(Long markStudentID);
}

package com.example.backend_service.repositories;

import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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

    @Query(value = """
            select m.mark_student_id from mark_student m 
            where m.exam_id = :examID;
            """,
            nativeQuery = true)
    Long getMarkStudentID_By_Exam_ExamID(@Param("examID") Long examID);

    MarkStudent findMarkStudentByMarkStudentID(Long markStudentID);

    @Query(value = """
            select odrjc.output_debug_java_core_id from mark_student m
            left join  detail_mark_student dms
            on m.mark_student_id = dms.mark_student_id
            left join result_question_java_core rqjc
            on dms.detail_mark_student_id = rqjc.detail_mark_student_id
            left join output_debug_result_java_core odrjc
            on rqjc.result_question_java_core_id = odrjc.result_question_java_core_id
            where m.mark_student_id = :markStudentID;
            """,
            nativeQuery = true)
    List<Long> getListOutputDebugResultJavaCoreID_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
            select rqjc.result_question_java_core_id from mark_student m
            left join  detail_mark_student dms
            on m.mark_student_id = dms.mark_student_id
            left join result_question_java_core rqjc
            on dms.detail_mark_student_id = rqjc.detail_mark_student_id
            where m.mark_student_id = :markStudentID;
            """,
            nativeQuery = true)
    List<Long> getListResultQuestionJavaCoreID_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
            select dms.detail_mark_student_id from mark_student m
            left join detail_mark_student dms
            on m.mark_student_id = dms.mark_student_id
            where m.mark_student_id = :markStudentID;
            """,
            nativeQuery = true)
    List<Long> getListDetailMarkStudentID_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
            select e.title_exam, e.type_exam,m.mark_exam from mark_student m
            right join exam e
            on m.exam_id = e.exam_id
            where m.student_id = :studentID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getListResultExam_By_StudentID (@Param("studentID") String studentID);
}

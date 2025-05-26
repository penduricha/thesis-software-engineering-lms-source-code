package com.example.backend_service.repositories;

import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MarkStudentRepository extends JpaRepository<MarkStudent, Long> {
    @Query(value = """
            select * from mark_student where exam_id = :examID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> findMarkStudentByExam_ExamID(@Param("examID") Long examID);

    //MarkStudent findMarkStudentByMarkStudentID(Long markStudentID);

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
            select 
            m.mark_student_id, e.title_exam, e.type_exam,
            m.mark_exam, e.exam_id, e.retake_exam, e.topic_exam from mark_student m
            right join exam e
            on m.exam_id = e.exam_id
            where m.student_id = :studentID order by e.title_exam;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getListResultExam_By_StudentID (@Param("studentID") String studentID);

    @Query(value = """
            select s.student_id,
                   s.last_name,
                   s.first_name,
                   s.gender,
                   s.date_of_birth,
                   m.mark_exam,
                   m.mark_student_id,
                   e.retake_exam
            from mark_student m
            right join student s
            on m.student_id = s.student_id
            right join exam e
            on m.exam_id = e.exam_id
            where e.exam_id = :examID;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getListStudentMark_By_ExamID(@Param("examID") Long examID);

    @Query(value = """
            select
                case
                    when mark_exam >= 0 and mark_exam < 4 then '0 to 4'
                    when mark_exam >= 4 and mark_exam < 6 then '4 to 6'
                    when mark_exam >= 6 and mark_exam < 8 then '6 to 8'
                    when mark_exam >= 8 and mark_exam <= 10 then '8 to 10'
                    end as mark_range,
                count(*) as count
            from mark_student where exam_id = :examID
            group by mark_range;
            """,
            nativeQuery = true)
    List<Map<String, Object>> getGroupByMarkExam_By_ExamID(@Param("examID") Long examID);

    @Query(value = """
            select round(avg(mark_exam),2) as average_mark_exam
            from mark_student where exam_id = :examID;
            """,
            nativeQuery = true)
    Double getAverageMarkExam_By_ExamID(@Param("examID") Long examID);

    @Query(value = """
            with mark_ranges as (
                select '0 to 1' as mark_range, 0 as range_start, 1 as range_end union all
                select '1 to 2', 1, 2 union all
                select '2 to 3', 2, 3 union all
                select '3 to 4', 3, 4 union all
                select '4 to 5', 4, 5 union all
                select '5 to 6', 5, 6 union all
                select '6 to 7', 6, 7 union all
                select '7 to 8', 7, 8 union all
                select '8 to 9', 8, 9 union all
                select '9 to 10', 9, 11
            )
            select
                m.mark_range,
                coalesce(count(s.mark_exam), 0) AS count
            from
                mark_ranges m
                    left join
                mark_student s on
                    s.mark_exam >= m.range_start and
                    s.mark_exam < m.range_end and s.exam_id = :examID
            group by
                m.mark_range
            order by
                m.range_start;
           """,
            nativeQuery = true)
    List<Map<String, Object>> getGroupBy_ZeroToTen_MarkExam_By_ExamID(@Param("examID") Long examID);
}

package com.example.backend_service.repositories;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface DetailMarkStudentRepository extends JpaRepository<DetailMarkStudent, Long> {
    DetailMarkStudent findDetailMarkStudentByDetailMarkStudentID(Long detailMarkStudentID);

//    //MarkStudent findDetailMarkStudentByDetailMarkStudentID(Long detailMarkStudentID);
//    MarkStudent findMark(Long detailMarkStudentID);
    @Query(value = """
                select d.mark_student_id from detail_mark_student d
                where d.detail_mark_student_id = :detailMarkStudentID
                """,
            nativeQuery = true)
    Long getMarkStudentID_By_DetailMarkStudentID(@Param("detailMarkStudentID") Long detailMarkStudentID);

    @Query(value = """
                select dms.detail_mark_student_id,
                      dms.date_submitted,
                      dms.detail_mark_exam
               from detail_mark_student dms
               left join mark_student ms
               on dms.mark_student_id = ms.mark_student_id
               left join exam e
               on ms.exam_id = e.exam_id
               where ms.mark_student_id = :markStudentID;
                """,
            nativeQuery = true)
    List<Map<String, Object>> getListDetailMarkStudent_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
                select max(d.detail_mark_exam) from detail_mark_student d
                where mark_student_id = :markStudentID;
                """,
            nativeQuery = true)
    Double getMaxDetailMarkExam_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
                select avg(d.detail_mark_exam) from detail_mark_student d
                where mark_student_id = :markStudentID;
                """,
            nativeQuery = true)
    Double getAvgDetailMarkExam_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

    @Query(value = """
                select detail_mark_exam
                from detail_mark_student
                where mark_student_id = :markStudentID
                and date_submitted = (
                    select max(date_submitted)
                    from detail_mark_student
                    where mark_student_id = :markStudentID
                )
                group by detail_mark_exam;
                """,
            nativeQuery = true)
    Double getLastSubmittedDetailMarkExam_By_MarkStudentID(@Param("markStudentID") Long markStudentID);
}

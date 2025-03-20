package com.example.backend_service.repositories;

import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.models.MarkStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}

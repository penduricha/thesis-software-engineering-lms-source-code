package com.example.backend_service.repositories;

import com.example.backend_service.models.DetailAnswerJavaClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailAnswerJavaClassRepository extends JpaRepository<DetailAnswerJavaClass, Long> {
        @Query(value = """
                select dajc.detail_answer_java_classid from mark_student ms
                right join detail_mark_student dms
                on ms.mark_student_id = dms.mark_student_id
                right join detail_answer_java_class dajc
                on dms.detail_mark_student_id = dajc.detail_mark_student_id
                where ms.mark_student_id = :markStudentID;
            """,
            nativeQuery = true)
        List<Long> getListDetailAnswerJavaClassID_By_MarkStudentID(@Param("markStudentID") Long markStudentID);

        //Hiển thị danh sách detail answer java class by detailMarkStudentID
        List<DetailAnswerJavaClass>
                findDetailAnswerJavaClassesByDetailMarkStudent_DetailMarkStudentID(Long detailMarkStudentID);
}

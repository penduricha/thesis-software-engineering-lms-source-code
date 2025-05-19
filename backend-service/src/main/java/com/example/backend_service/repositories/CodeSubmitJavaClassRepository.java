package com.example.backend_service.repositories;

import com.example.backend_service.models.CodeSubmitJavaClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeSubmitJavaClassRepository extends JpaRepository<CodeSubmitJavaClass, Long> {
    CodeSubmitJavaClass findCodeSubmitJavaClassByDetailMarkStudent_DetailMarkStudentID(Long detailMarkStudentID);

    @Query(value = """
            select csjc.code_submit_java_class_id from code_submit_java_class csjc
            right join detail_mark_student dms
            on csjc.detail_mark_student_id = dms.detail_mark_student_id
            right join mark_student ms
            on dms.mark_student_id = ms.mark_student_id
            where ms.mark_student_id = :markStudentID;
            """,
            nativeQuery = true)
    List<Long> getListCodeSubmitJavaClassID_By_MarkStudentID(@Param("markStudentID") Long markStudentID);
}

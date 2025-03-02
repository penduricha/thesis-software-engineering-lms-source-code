package com.example.backend_service.repositories;

import com.example.backend_service.models.CodeStorageSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface CodeStorageServiceRepository extends JpaRepository<CodeStorageSave,Long> {

    @Query(value = """
            select code_save from code_storage_save
            where student_id = :studentID 
            and index_question_save = :indexQuestion;
            """,
            nativeQuery = true)
    String getCodeSave_By_StudentID_IndexQuestion
            (@Param("studentID") String studentID,@Param("indexQuestion") int indexQuestion);

    @Query(value = """
            select
                c.code_storage_save_id,
                c.code_save,
                c.index_question_save,
                c.student_id from code_storage_save c
            where student_id = :studentID  and index_question_save = :indexQuestion;
            """,
            nativeQuery = true)
    Map<String, Object> findCodeStorageBy_StudentID_IndexQuestion
            (@Param("studentID") String studentID,@Param("indexQuestion") int indexQuestion);
}

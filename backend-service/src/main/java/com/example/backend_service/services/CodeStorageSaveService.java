package com.example.backend_service.services;

import com.example.backend_service.models.CodeStorageSave;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.CodeStorageServiceRepository;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_CodeStorageSaveService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CodeStorageSaveService implements I_CodeStorageSaveService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CodeStorageServiceRepository codeStorageServiceRepository;

    private final StudentRepository studentRepository;

    public CodeStorageSaveService(CodeStorageServiceRepository codeStorageServiceRepository, StudentRepository studentRepository) {
        this.codeStorageServiceRepository = codeStorageServiceRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    //annotation nay tao luong sql query
    public CodeStorageSave saveCode(CodeStorageSave codeStorageSave, String studentID) throws JpaSystemException {
        Student student = studentRepository.findStudentByStudentID(studentID);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + studentID);
        }
        Map<String, Object> existingCodeStorage =
                findCodeStorageBy_StudentID_IndexQuestion(studentID, codeStorageSave.getIndexQuestionSave());
        if (existingCodeStorage.isEmpty()) {
            student.getCodeStorageSaves().add(codeStorageSave);
            codeStorageSave.setStudent(student);
            return codeStorageServiceRepository.save(codeStorageSave);
        } else {
            // If an entry exists, update the existing code storage
            String sqlUpdate = "update code_storage_save set code_save = :codeSave \n" +
                    "where student_id = :studentID and index_question_save = :indexQuestionSave;";

            entityManager.createNativeQuery(sqlUpdate)
                    .setParameter("codeSave", codeStorageSave.getCodeSave())
                    .setParameter("studentID", studentID)
                    .setParameter("indexQuestionSave", codeStorageSave.getIndexQuestionSave())
                    .executeUpdate();

            // Optionally, you might want to return the updated code storage or some confirmation
            // or retrieve the updated entity if needed
            return codeStorageSave;
        }
    }

    @Override
    public String getCodeSave_By_StudentID_IndexQuestion(String studentID, int indexQuestion) throws JpaSystemException {
        return codeStorageServiceRepository.getCodeSave_By_StudentID_IndexQuestion(studentID, indexQuestion);
    }

    @Override
    public Map<String, Object> findCodeStorageBy_StudentID_IndexQuestion
            (String studentID, int indexQuestion) throws JpaSystemException {
        Map<String, Object> mapQuery = codeStorageServiceRepository
                .findCodeStorageBy_StudentID_IndexQuestion(studentID,indexQuestion);
        if(!mapQuery.isEmpty()) {
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("codeStorageSaveID", mapQuery.get("code_storage_save_id"));
            returnMap.put("codeSave", mapQuery.get("code_save"));
            returnMap.put("indexQuestionSave", mapQuery.get("index_question_save"));
            returnMap.put("studentID", mapQuery.get("student_id"));
            return returnMap;
        }
        return new HashMap<>();
    }
}

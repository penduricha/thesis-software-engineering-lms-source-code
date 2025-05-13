package com.example.backend_service.services;

import com.example.backend_service.models.CodeSubmitJavaClass;
import com.example.backend_service.models.DetailMarkStudent;
import com.example.backend_service.repositories.CodeSubmitJavaClassRepository;
import com.example.backend_service.repositories.DetailMarkStudentRepository;
import com.example.backend_service.services.i_service.I_CodeSubmitJavaClassService;
import org.springframework.stereotype.Service;

@Service
public class CodeSubmitJavaClassService implements I_CodeSubmitJavaClassService {
    private final CodeSubmitJavaClassRepository codeSubmitJavaClassRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    public CodeSubmitJavaClassService(CodeSubmitJavaClassRepository codeSubmitJavaClassRepository, DetailMarkStudentRepository detailMarkStudentRepository) {
        this.codeSubmitJavaClassRepository = codeSubmitJavaClassRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
    }

    @Override
    public CodeSubmitJavaClass getCodeSubmitJavaClass_By_DetailMarkStudentID(Long detailMarkStudentID) {
        DetailMarkStudent detailMarkStudent = detailMarkStudentRepository
                .findDetailMarkStudentByDetailMarkStudentID(detailMarkStudentID);
        if(detailMarkStudent != null) {
            return codeSubmitJavaClassRepository
                    .findCodeSubmitJavaClassByDetailMarkStudent_DetailMarkStudentID
                            (detailMarkStudent.getDetailMarkStudentID());
        }
        return new CodeSubmitJavaClass();
    }

    /*
        Yeu cau se co ham nhu sau:

     */
}

package com.example.backend_service.services;

import com.example.backend_service.repositories.CodeSubmitJavaClassRepository;
import com.example.backend_service.services.i_service.I_CodeSubmitJavaClassService;
import org.springframework.stereotype.Service;

@Service
public class CodeSubmitJavaClassService implements I_CodeSubmitJavaClassService {
    private final CodeSubmitJavaClassRepository codeSubmitJavaClassRepository;

    public CodeSubmitJavaClassService(CodeSubmitJavaClassRepository codeSubmitJavaClassRepository) {
        this.codeSubmitJavaClassRepository = codeSubmitJavaClassRepository;
    }

    /*
        Yeu cau se co ham nhu sau:

     */
}

package com.example.backend_service.services.i_service;

import com.example.backend_service.models.CodeStorageSave;

import java.util.Map;

public interface I_CodeStorageSaveService {
    public CodeStorageSave saveCode(CodeStorageSave codeStorageSave, String studentID);

    public String getCodeSave_By_StudentID_IndexQuestion (String studentID, int indexQuestion);

    public Map<String, Object> findCodeStorageBy_StudentID_IndexQuestion (String studentID, int indexQuestion);

    public String delete_CodeStorageBy_StudentID(String studentID);
}

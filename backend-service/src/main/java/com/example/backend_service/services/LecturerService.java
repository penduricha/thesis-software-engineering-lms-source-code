package com.example.backend_service.services;

import com.example.backend_service.models.Lecturer;

import com.example.backend_service.repositories.LecturerRepository;


import com.example.backend_service.services.i_service.I_LecturerService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import java.util.Map;

@Service
public class LecturerService implements I_LecturerService {
    private final LecturerRepository lecturerRepository;

    public LecturerService(LecturerRepository LecturerRepository) {
        this.lecturerRepository = LecturerRepository;
    }

//    @Override
//    public boolean persistLecturers(List<Lecturer> lecturers) {
//        return false;
//    }

    @Override
    public Lecturer findLecturerByLecturerID(String LecturerID) throws JpaSystemException {
        return lecturerRepository.findLecturerByLecturerID(LecturerID);
    }

    @Override
    public Lecturer updatePasswordLecturer(String lecturerID, String newPassword) throws JpaSystemException {
        Lecturer LecturerFind = findLecturerByLecturerID(lecturerID);
        if(LecturerFind != null) {
            LecturerFind.setPassword(newPassword);
            return lecturerRepository.save(LecturerFind);
        }
        return null;
    }

    @Override
    public Map<String, Object> findLecturerInformation_By_LecturerID(String lecturerID) throws JpaSystemException {
        Lecturer LecturerFind = findLecturerByLecturerID(lecturerID);
        Map<String, Object> LecturerInfo = new HashMap<>();
        if (LecturerFind != null) {
            LecturerInfo.put("LecturerID", LecturerFind.getLecturerID());
            LecturerInfo.put("name", LecturerFind.getName());
            LecturerInfo.put("email", LecturerFind.getEmail());
            LecturerInfo.put("phoneNumber", LecturerFind.getPhoneNumber());
            return LecturerInfo;
        }
        return null;
    }

    @Override
    public Map<String, Object> findDetailInformation_By_LecturerID(String lecturerID) throws JpaSystemException {
        Lecturer LecturerFound = findLecturerByLecturerID(lecturerID);
        if(LecturerFound != null) {
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("email", LecturerFound.getEmail());
            newMap.put("name", LecturerFound.getName());
            newMap.put("phoneNumber", LecturerFound.getPhoneNumber());
            return newMap;
        }
        return new HashMap<>();
    }
}

package com.example.backend_service.services.i_service;


import com.example.backend_service.models.Lecturer;

import java.util.List;
import java.util.Map;

public interface I_LecturerService {
    //public boolean persistLectures(List<Lecturer> lectures);

    public Lecturer findLecturerByLecturerID(String lecturerID);

    public Lecturer updatePasswordLecturer(String lecturerID, String newPassword);

    public Map<String,Object> findLecturerInformation_By_LecturerID(String lectureID);

    public Map<String, Object> findDetailInformation_By_LecturerID(String lectureID);
}

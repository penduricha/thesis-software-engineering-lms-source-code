package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Lecture;

import java.util.List;
import java.util.Map;

public interface I_LectureService {
    public boolean persistLectures(List<Lecture> lectures);

    public Lecture findLectureByLectureID(String lectureID);

    public Lecture updatePasswordLecture(String lectureID, String newPassword);

    public Map<String,Object> findLectureInformation_By_LectureID(String lectureID);
}

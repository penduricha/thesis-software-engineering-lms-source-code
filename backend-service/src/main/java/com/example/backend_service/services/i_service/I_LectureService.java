package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Lecture;

import java.util.List;

public interface I_LectureService {
    public boolean persistLectures(List<Lecture> lectures);
}

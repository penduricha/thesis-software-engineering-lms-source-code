package com.example.backend_service.services;

import com.example.backend_service.models.Lecture;

import com.example.backend_service.repositories.LectureRepository;

import com.example.backend_service.services.i_service.I_LectureService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LectureService implements I_LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public boolean persistLectures(List<Lecture> lectures) {
        return false;
    }

    @Override
    public Lecture findLectureByLectureID(String lectureID) throws JpaSystemException {
        return lectureRepository.findLectureByLectureID(lectureID);
    }

    @Override
    public Lecture updatePasswordLecture(String lectureID, String newPassword) {
        Lecture lectureFind = findLectureByLectureID(lectureID);
        if(lectureFind != null) {
            lectureFind.setPassword(newPassword);
            return lectureRepository.save(lectureFind);
        }
        return null;
    }

    @Override
    public Map<String, Object> findLectureInformation_By_LectureID(String lectureID) {
        Lecture lectureFind = findLectureByLectureID(lectureID);
        Map<String, Object> lectureInfo = new HashMap<>();
        if (lectureFind != null) {
            lectureInfo.put("lectureID", lectureFind.getLectureID());
            lectureInfo.put("name", lectureFind.getName());
            lectureInfo.put("email", lectureFind.getEmail());
            lectureInfo.put("phoneNumber", lectureFind.getPhoneNumber());
            return lectureInfo;
        }
        return null;
    }
}

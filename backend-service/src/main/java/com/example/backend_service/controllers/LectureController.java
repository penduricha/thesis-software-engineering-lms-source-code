package com.example.backend_service.controllers;

import com.example.backend_service.models.*;
import com.example.backend_service.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LectureController {
    private final LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

//    public ResponseEntity<Lecture> findLectureByLectureID(@PathVariable String lectureID) throws HttpClientErrorException {
//        Lecture lecture = lectureService.findLectureByLectureID(lectureID);
//        System.out.println(lecture);
//        if(lecture != null)
//            return ResponseEntity.ok(lectureService.findLectureByLectureID(lectureID));
//        else
//            return ResponseEntity.notFound().build();
//    }
    @GetMapping("/lecture/lectureID_password/{lectureID}")
    public Map<String, Object> findLectureByLectureID(@PathVariable String lectureID) throws HttpClientErrorException {
        Map<String, Object> lectureMap = new HashMap<>();
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture lecture = lectureService.findLectureByLectureID(lectureIDFind);
            if (lecture != null) {
                lectureMap.put("lectureID", lecture.getLectureID());
                lectureMap.put("password", lecture.getPassword());
            }
        }
        return lectureMap;
    }

    @GetMapping("/lecture/lectureID_name/{lectureID}")
    public Map<String, Object> findLectureID_Name_ByLectureId(@PathVariable String lectureID) throws HttpClientErrorException{
        Map<String, Object> lectureNameMap = new HashMap<>();
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture lecture = lectureService.findLectureByLectureID(lectureIDFind);
            if (lecture != null) {
                lectureNameMap.put("lectureID", lecture.getLectureID());
                lectureNameMap.put("name", lecture.getName());
            }
        }
        return lectureNameMap;
    }

    @GetMapping("/lecture/getPassword_By_LectureID/{lectureID}")
    public ResponseEntity<String> updatePasswordStudent(@PathVariable String lectureID) throws HttpClientErrorException {
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture lecture = lectureService.findLectureByLectureID(lectureIDFind);
            return ResponseEntity.ok(lecture.getPassword());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/lecture/updatePasswordLecture/{lectureID}/{newPassword}")
    public ResponseEntity<String> updatePasswordStudent(@PathVariable String lectureID, @PathVariable String newPassword) throws HttpClientErrorException {
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture updatedLecture = lectureService.updatePasswordLecture(lectureIDFind, newPassword);
            return ResponseEntity.ok(updatedLecture.getPassword());
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping("/lecture/updatePasswordLecture/{lectureID}/{newPassword}")
//    public ResponseEntity<String> updatePasswordStudent(@PathVariable String lectureID, @PathVariable String newPassword) throws HttpClientErrorException {
//        Lecture updatedLecture = lectureService.updatePasswordLecture(lectureID, newPassword);
//        return ResponseEntity.ok(updatedLecture.getPassword());
//    }

}

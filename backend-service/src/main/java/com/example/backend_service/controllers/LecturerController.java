package com.example.backend_service.controllers;

import com.example.backend_service.models.*;
import com.example.backend_service.services.ExecuteJavaService;


import com.example.backend_service.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LecturerController {
    private final LecturerService lecturerService;

    private final ExecuteJavaService executeJavaService;

    @Autowired
    public LecturerController(LecturerService lecturerService, ExecuteJavaService executeJavaService) {
        this.lecturerService = lecturerService;
        this.executeJavaService = executeJavaService;
    }

//    public ResponseEntity<Lecture> findLectureByLectureID(@PathVariable String lectureID) throws HttpClientErrorException {
//        Lecture lecture = lectureService.findLectureByLectureID(lectureID);
//        System.out.println(lecture);
//        if(lecture != null)
//            return ResponseEntity.ok(lectureService.findLectureByLectureID(lectureID));
//        else
//            return ResponseEntity.notFound().build();
//    }
    @GetMapping("/lecturer/lecturerID_password/{lecturerID}")
    public ResponseEntity<Map<String, Object>> findLectureByLecturerID(@PathVariable String lecturerID) throws HttpClientErrorException {
        Map<String, Object> lectureMap = new HashMap<>();
        if(lecturerID.charAt(0) == '0'){
            String lectureIDFind = lecturerID.substring(1);
            Lecturer lecture = lecturerService.findLecturerByLecturerID(lectureIDFind);
            if (lecture != null) {
                lectureMap.put("lecturerID", lecture.getLecturerID());
                lectureMap.put("password", lecture.getPassword());
            }
        }
        return ResponseEntity.ok(lectureMap);
    }

    @GetMapping("/lecturer/lecturerID_name/{lecturerID}")
    public ResponseEntity<Map<String, Object>> findLectureID_Name_ByLectureID(@PathVariable String lecturerID) throws HttpClientErrorException{
        Map<String, Object> lectureNameMap = new HashMap<>();
        if(lecturerID.charAt(0) == '0'){
            String lectureIDFind = lecturerID.substring(1);
            Lecturer lecture = lecturerService.findLecturerByLecturerID(lectureIDFind);
            if (lecture != null) {
                lectureNameMap.put("lecturerID", lecture.getLecturerID());
                lectureNameMap.put("name", lecture.getName());
            }
        }
        return ResponseEntity.ok(lectureNameMap);
    }

    @GetMapping("/lecturer/find-detail-information-lecturer/{lecturerID}")
    public ResponseEntity<Map<String, Object>> findDetailInformation_By_LecturerID(@PathVariable String lecturerID)
            throws HttpClientErrorException{
        return ResponseEntity.ok(lecturerService.findDetailInformation_By_LecturerID(covertLectureID(lecturerID)));
    }

    @GetMapping("/lecturer/getPassword_By_LecturerID/{lecturerID}")
    public ResponseEntity<String> getLecturePassword(@PathVariable String lecturerID) throws HttpClientErrorException {
        if(lecturerID.charAt(0) == '0'){
            String lecturerIDFind = lecturerID.substring(1);
            Lecturer lecture = lecturerService.findLecturerByLecturerID(lecturerIDFind);
            return ResponseEntity.ok(lecture.getPassword());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/lecturer/updatePasswordLecturer/{lecturerID}/{newPassword}")
    public ResponseEntity<String> updatePasswordLecturer(@PathVariable String lecturerID, @PathVariable String newPassword) throws HttpClientErrorException {
        if(lecturerID.charAt(0) == '0'){
            String lectureIDFind = lecturerID.substring(1);
            Lecturer updatedLecture = lecturerService.updatePasswordLecturer(lectureIDFind, newPassword);
            return ResponseEntity.ok(updatedLecture.getPassword());
        }
        return ResponseEntity.notFound().build();
    }

    public String covertLectureID(String lecturerID) {
        return LecturerIDFunc.covertLectureID(lecturerID);
    }

    @GetMapping("/lecturer/lecturer_Information_By_LectureID/{lecturerID}")
    public ResponseEntity<Map<String, Object>> getLecturer_Information(@PathVariable String lecturerID) throws HttpClientErrorException {
        return ResponseEntity.ok(lecturerService.findLecturerInformation_By_LecturerID(covertLectureID(lecturerID)));
    }

//    public ResponseEntity<>


//    @GetMapping("/lecture/updatePasswordLecture/{lectureID}/{newPassword}")
//    public ResponseEntity<String> updatePasswordStudent(@PathVariable String lectureID, @PathVariable String newPassword) throws HttpClientErrorException {
//        Lecture updatedLecture = lectureService.updatePasswordLecture(lectureID, newPassword);
//        return ResponseEntity.ok(updatedLecture.getPassword());
//    }
    @PostMapping("/lecturer/compiler_main_code_in_create_update_question")
    public ResponseEntity<Boolean> post_TestCompiler_MainCode_By_LectureID(@RequestBody Map<String, Object> mapCodeMainLecture) {
        return ResponseEntity.ok(executeJavaService.testCompiler_MainCode_By_LecturerID(mapCodeMainLecture));
    }
}

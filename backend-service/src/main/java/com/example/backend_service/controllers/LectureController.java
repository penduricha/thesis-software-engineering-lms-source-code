package com.example.backend_service.controllers;

import com.example.backend_service.models.*;
import com.example.backend_service.services.ExecuteJavaService;
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

    private final ExecuteJavaService executeJavaService;

    @Autowired
    public LectureController(LectureService lectureService, ExecuteJavaService executeJavaService) {
        this.lectureService = lectureService;
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
    @GetMapping("/lecture/lectureID_password/{lectureID}")
    public ResponseEntity<Map<String, Object>> findLectureByLectureID(@PathVariable String lectureID) throws HttpClientErrorException {
        Map<String, Object> lectureMap = new HashMap<>();
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture lecture = lectureService.findLectureByLectureID(lectureIDFind);
            if (lecture != null) {
                lectureMap.put("lectureID", lecture.getLectureID());
                lectureMap.put("password", lecture.getPassword());
            }
        }
        return ResponseEntity.ok(lectureMap);
    }

    @GetMapping("/lecture/lectureID_name/{lectureID}")
    public ResponseEntity<Map<String, Object>> findLectureID_Name_ByLectureId(@PathVariable String lectureID) throws HttpClientErrorException{
        Map<String, Object> lectureNameMap = new HashMap<>();
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            Lecture lecture = lectureService.findLectureByLectureID(lectureIDFind);
            if (lecture != null) {
                lectureNameMap.put("lectureID", lecture.getLectureID());
                lectureNameMap.put("name", lecture.getName());
            }
        }
        return ResponseEntity.ok(lectureNameMap);
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

    public String covertLectureID(String lectureID) {
        return LectureIDFunc.covertLectureID(lectureID);
    }

    @GetMapping("/lecture/lecture_Information_By_LectureID/{lectureID}")
    public ResponseEntity<Map<String, Object>> getLecture_Information(@PathVariable String lectureID) throws HttpClientErrorException {
        return ResponseEntity.ok(lectureService.findLectureInformation_By_LectureID(covertLectureID(lectureID)));
    }

//    public ResponseEntity<>


//    @GetMapping("/lecture/updatePasswordLecture/{lectureID}/{newPassword}")
//    public ResponseEntity<String> updatePasswordStudent(@PathVariable String lectureID, @PathVariable String newPassword) throws HttpClientErrorException {
//        Lecture updatedLecture = lectureService.updatePasswordLecture(lectureID, newPassword);
//        return ResponseEntity.ok(updatedLecture.getPassword());
//    }
    @PostMapping("/lecture/compiler_main_code_in_create_update_question")
    public ResponseEntity<Boolean> post_TestCompiler_MainCode_By_LectureID(@RequestBody Map<String, Object> mapCodeMainLecture) {
        return ResponseEntity.ok(executeJavaService.testCompiler_MainCode_By_LectureID(mapCodeMainLecture));
    }
}

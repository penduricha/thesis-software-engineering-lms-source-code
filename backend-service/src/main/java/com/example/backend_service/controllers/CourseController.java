package com.example.backend_service.controllers;

import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses/lecturerID/{lecturerID}")
    public ResponseEntity<List<Map<String, Object>>> getCourses_By_LecturerID(@PathVariable String lecturerID) throws HttpClientErrorException {
        List<Map<String, Object>> list = new ArrayList<>();
        if(lecturerID.charAt(0) == '0'){
            String lecturerIDFind = lecturerID.substring(1);
            List<Map<String, Object>> queryMap = courseRepository.getCoursesByLecturerID(lecturerIDFind);
            if (queryMap != null) {
                for(Map<String, Object> map: queryMap) {
                    Map<String, Object> courseMap = new HashMap<>();
                    courseMap.put("courseID", map.get("course_id"));
                    courseMap.put("className", map.get("class_name"));
                    courseMap.put("courseName", map.get("course_name"));
                    courseMap.put("semester", map.get("semester"));
                    courseMap.put("startYear", map.get("start_year"));
                    courseMap.put("endYear", map.get("end_year"));
                    list.add(courseMap);
                }
            }
        }
        return ResponseEntity.ok(list);
    }

    public String covertLecturerID(String lecturerID) {
        return LecturerIDFunc.covertLectureID(lecturerID);
    }

    @GetMapping("/course/get_course_by_lecturerID_courseID/{lecturerID}/{courseID}")
    public ResponseEntity<Map<String, Object>> getCourse_By_LecturerID_CourseID(@PathVariable String lecturerID,@PathVariable Long courseID) throws HttpClientErrorException {
        return ResponseEntity.ok(courseService.getCourse_By_LecturerID_CourseID(covertLecturerID(lecturerID),courseID));
    }

    @GetMapping("/course/get_course_by_examID/{examID}")
    public ResponseEntity<Long> getCourseIDByExamID(@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(courseService.getCourseID_By_ExamID(examID));
    }
}

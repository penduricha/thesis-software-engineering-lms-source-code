package com.example.backend_service.controllers;

import com.example.backend_service.models.Lecture;
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

    @GetMapping("/courses/lectureID/{lectureID}")
    public ResponseEntity<List<Map<String, Object>>> getCourses_By_LectureID(@PathVariable String lectureID) throws HttpClientErrorException {
        List<Map<String, Object>> list = new ArrayList<>();
        if(lectureID.charAt(0) == '0'){
            String lectureIDFind = lectureID.substring(1);
            List<Map<String, Object>> queryMap = courseRepository.getCoursesByLectureID(lectureIDFind);
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

    public String covertLectureID(String lectureID) {
        return LectureIDFunc.covertLectureID(lectureID);
    }

    @GetMapping("/course/get_course_by_lectureID_courseID/{lectureID}/{courseID}")
    public ResponseEntity<Map<String, Object>> getCourse_By_LectureID_CourseID(@PathVariable String lectureID,@PathVariable Long courseID) throws HttpClientErrorException {
        return ResponseEntity.ok(courseService.getCourse_By_LectureID_CourseID(covertLectureID(lectureID),courseID));
    }

    @GetMapping("/course/get_course_by_examID/{examID}")
    public ResponseEntity<Long> getCourseIDByExamID(@PathVariable Long examID) throws HttpClientErrorException {
        return ResponseEntity.ok(courseService.getCourseID_By_ExamID(examID));
    }
}

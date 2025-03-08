package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Course;
import java.util.*;
public interface I_CourseService {
    public List<Course> addListCourses(List<Course> courses);

    public List<Course> getListCoursesByLectureId(String lectureId);

    public Map<String, Object> getCourse_By_LectureID_CourseID(String lectureID, Long courseID);
}

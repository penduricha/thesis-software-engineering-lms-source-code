package com.example.backend_service.services.i_service;

import com.example.backend_service.models.Course;
import java.util.*;
public interface I_CourseService {
    public List<Course> addListCourses(List<Course> courses);

    public List<Course> getListCoursesByLecturerId(String lecturerId);

    public Map<String, Object> getCourse_By_LecturerID_CourseID(String lecturerID, Long courseID);

    public Long getCourseID_By_ExamID(Long examID);
}

package com.example.backend_service.services;

import com.example.backend_service.models.Course;
import com.example.backend_service.models.Exam;
import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.services.i_service.I_CourseService;
import jakarta.persistence.EntityManager;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CourseService implements I_CourseService {
    private final CourseRepository courseRepository;

    private EntityManager entityManager;

    private final ExamRepository examRepository;

    public CourseService(CourseRepository courseRepository, ExamRepository examRepository) {
        this.courseRepository = courseRepository;
        this.examRepository = examRepository;
    }

    @Override
    public List<Course> addListCourses(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    @Override
    public List<Course> getListCoursesByLectureId(String lectureId) {
        return List.of();
    }

    @Override
    public Map<String, Object> getCourse_By_LectureID_CourseID(String lectureID, Long courseID) throws JpaSystemException  {
        List<Map<String, Object>> queryMapCourse = courseRepository.getCoursesByLectureID(lectureID);
        List<Map<String, Object>> filteredMapByCourseID = queryMapCourse.stream()
                .filter(map -> map.get("course_id").equals(courseID))
                .toList();
        if (!filteredMapByCourseID.isEmpty()) {
            Map<String, Object> returnMapCourse = new HashMap<>();
            Map<String, Object> firstMapCourse = filteredMapByCourseID.getFirst();
            returnMapCourse.put("courseID", firstMapCourse.get("course_id"));
            returnMapCourse.put("className", firstMapCourse.get("class_name"));
            returnMapCourse.put("courseName", firstMapCourse.get("course_name"));
            returnMapCourse.put("semester", firstMapCourse.get("semester"));
            returnMapCourse.put("startYear", firstMapCourse.get("start_year"));
            returnMapCourse.put("endYear", firstMapCourse.get("end_year"));
            return returnMapCourse;
        } else {
            return new HashMap<>();
        }
    }

    @Override
    public Long getCourseID_By_ExamID(Long examID) {
        Exam examFound = examRepository.findExamByExamID(examID);
        if(examFound != null) {
            return courseRepository.findCourseIDByExamID(examID);
        }
        return null;
    }
}

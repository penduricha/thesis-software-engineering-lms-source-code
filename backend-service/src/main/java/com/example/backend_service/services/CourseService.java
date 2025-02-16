package com.example.backend_service.services;

import com.example.backend_service.models.Course;
import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.services.i_service.I_CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements I_CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> addListCourses(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }
}

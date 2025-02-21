package com.example.backend_service.services;

import com.example.backend_service.models.Course;
import com.example.backend_service.models.Exam;
import com.example.backend_service.repositories.CourseRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.services.i_service.I_ExamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
public class ExamService implements I_ExamService {

    private final ExamRepository examRepository;

    private final CourseRepository courseRepository;

    public ExamService(ExamRepository examRepository, CourseRepository courseRepository) {
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Exam createExam(Exam exam, Long courseID) throws JpaSystemException {
        Course course = courseRepository.findCourseByCourseID(courseID);
        if(course != null) {
            exam.setCourse(course);
            course.getExams().add(exam);
            return examRepository.save(exam);
        }
        return null;
    }
}

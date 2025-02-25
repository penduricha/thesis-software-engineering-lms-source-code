package com.example.backend_service.services;


import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService implements I_StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean persistStudents(List<Student> students) {
        return false;
    }

    @Override
    public List<Student> getStudents() {
        return List.of();
    }

    @Override
    public Student findStudentByStudentId(String studentID) throws JpaSystemException {
        return studentRepository.findStudentByStudentID(studentID);
    }

    @Override
    public Student updatePasswordStudent(String studentID, String newPassword) throws JpaSystemException {
        Student studentFind = findStudentByStudentId(studentID);
        if(studentFind != null) {
            studentFind.setPassword(newPassword);
            return studentRepository.save(studentFind);
        }
        return null;
    }

    @Override
    public Map<String,Object> findStudentInformation_By_StudentID(String studentID) throws JpaSystemException {
        Student studentFind = findStudentByStudentId(studentID);
        Map<String, Object> studentInfo = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(studentFind != null) {
            studentInfo.put("studentID", studentFind.getStudentID());
            studentInfo.put("lastName", studentFind.getLastName());
            studentInfo.put("firstName", studentFind.getFirstName());
            studentInfo.put("gender", studentFind.isGender() ? "Male" : "Female");
            //format date of birth
            LocalDate dateOfBirth = studentFind.getDateOfBirth();
            studentInfo.put("dateOfBirth", dateOfBirth.format(formatter));
            return studentInfo;
        }
        return null;
    }

    @Override
    public Long getCourseIDByStudentID(String studentID) {
        Student studentFind = findStudentByStudentId(studentID);
        if(studentFind != null) {
            return studentFind.getCourse().getCourseID();
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getExams_Calendar_Student_By_StartDate(String studentID, int yearStartDate, int monthStartDate, int dateStartDate) {
        String yearStartDateString = Integer.toString(yearStartDate);
        String monthStartDateString = Integer.toString(monthStartDate);
        String dateStartDateString = Integer.toString(dateStartDate);
        String dateFormat = yearStartDateString + "-" + monthStartDateString + "-" + dateStartDateString;
        return studentRepository.getExams_Calendar_Student_By_StartDate(studentID, dateFormat).stream()
                .map(originalMap -> {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("examID", originalMap.get("exam_id"));
                    newMap.put("titleExam", originalMap.get("title_exam"));
                    newMap.put("typeExam", originalMap.get("type_exam"));
                    newMap.put("startDate", originalMap.get("start_date"));
                    newMap.put("endDate", originalMap.get("end_date"));
                    return newMap;
                })
                .collect(Collectors.toList());
    }

}

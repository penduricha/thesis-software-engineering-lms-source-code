package com.example.backend_service.services;


import com.example.backend_service.models.Exam;
import com.example.backend_service.models.Student;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.sql.Timestamp;

@Service
public class StudentService implements I_StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ExamRepository examRepository) {
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
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
        LocalDateTime now = LocalDateTime.now(); // Khởi tạo LocalDateTime now
        return studentRepository.getExams_Calendar_Student_By_StartDate(studentID, dateFormat).stream()
                .map(originalMap -> {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("examID", originalMap.get("exam_id"));
                    newMap.put("titleExam", originalMap.get("title_exam"));
                    newMap.put("typeExam", originalMap.get("type_exam"));
                    // Chuyển đổi start_date và end_date từ Timestamp sang LocalDateTime
                    Timestamp startDateTimestamp = (Timestamp) originalMap.get("start_date");
                    Timestamp endDateTimestamp = (Timestamp) originalMap.get("end_date");
                    LocalDateTime startDate = startDateTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    LocalDateTime endDate = endDateTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    newMap.put("startDate", startDate);
                    newMap.put("endDate", endDate);
                    if (now.isBefore(startDate)) {
                        newMap.put("status", "Locked");
                    } else if (now.isAfter(endDate)) {
                        newMap.put("status", "Overdue");
                    } else {
                        newMap.put("status", "Open");
                    }
                    return newMap;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long accessToExam_By_StudentID_ExamID(String studentID, Long examID) throws JpaSystemException {
        Student studentFind = findStudentByStudentId(studentID);
        Exam examFind = examRepository.findExamByExamID(examID);
        if(studentFind !=null && examFind !=null) {
            Map<String, Object> studentAccessFound = findStudent_Access_Exam_By_ExamID(examID);
            if(studentAccessFound.isEmpty()) {
                String sqlInsertRecord = "insert into student_access_exam (student_id, exam_id) values (?,?)";
                entityManager.createNativeQuery(sqlInsertRecord)
                        .setParameter(1, studentFind.getStudentID())
                        .setParameter(2, examFind.getExamID()).executeUpdate();
                return examFind.getExamID();
            }
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public String deleteAccessToExam_By_StudentID(String studentID, Long examID) throws JpaSystemException {
        Student studentFind = findStudentByStudentId(studentID);
        Exam examFind = examRepository.findExamByExamID(examID);
        if(studentFind !=null && examFind != null) {
            Map<String, Object> studentAccessFound = findStudent_Access_Exam_By_ExamID(examID);
            if(!studentAccessFound.isEmpty()) {
                String sqlDeleteRecord = "delete from student_access_exam where student_id = ? and exam_id = ? ";
                entityManager.createNativeQuery(sqlDeleteRecord)
                        .setParameter(1, studentFind.getStudentID())
                        .setParameter(2, examFind.getExamID())
                        .executeUpdate();
                return studentFind.getStudentID();
            }
            return null;
        }
        return null;
    }

    @Override
    public Map<String, Object> findStudent_Access_Exam_By_ExamID(Long examID) throws JpaSystemException {
        if(examRepository.findExamByExamID(examID) != null)
            return studentRepository.findStudent_Access_Exam_By_ExamID(examID);
        else
            return new HashMap<>();
    }

    @Override
    public String setDateTimeStartExam_By_StudentID(String studentID) throws JpaSystemException {
        Student studentFind = findStudentByStudentId(studentID);
        if(studentFind != null) {
            studentFind.setDateTimeStartExam(LocalDateTime.now());
            studentRepository.save(studentFind);
            return studentFind.getStudentID();
        }
        return null;
    }

    @Override
    public String resetDateTimeStartExam_By_StudentID(String studentID) throws JpaSystemException  {
        Student studentFind = findStudentByStudentId(studentID);
        if(studentFind != null) {
            studentFind.setDateTimeStartExam(null);
            studentRepository.save(studentFind);
            return studentFind.getStudentID();
        }
        return null;
    }

    @Override
    public Map<String, Object> get_Information_Student_Do_Exam(String studentID)
            throws JpaSystemException {
        Map<String, Object> queryMap = studentRepository.get_Information_Student_Do_Exam(studentID);
        if(!queryMap.isEmpty()) {
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("examID", queryMap.get("exam_id"));
            returnMap.put("remainMinutes", queryMap.get("remain_minutes"));
            //System.out.println(queryMap);
            return returnMap;
        }
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> get_Information_Student_Detail_By_StudentID(String studentID) throws JpaSystemException {
        //s.student_id, date_of_birth, last_name, first_name, gender, c.course_name, c.class_name
        Student studentFound = findStudentByStudentId(studentID);
        if(studentFound != null) {
            Map<String, Object> queryMap = studentRepository.get_Information_Student_Detail_By_StudentID(studentID);
            if(!queryMap.isEmpty()) {
                Map<String, Object> newMap = new HashMap<>();
                newMap.put("studentID", queryMap.get("student_id"));
                newMap.put("dateOfBirth", queryMap.get("date_of_birth"));
                newMap.put("lastName", queryMap.get("last_name"));
                newMap.put("firstName", queryMap.get("first_name"));
                newMap.put("gender", queryMap.get("gender"));
                newMap.put("courseName", queryMap.get("course_name"));
                newMap.put("className", queryMap.get("class_name"));
                return newMap;
            }
        }
        return new HashMap<>();
    }
}

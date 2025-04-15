package com.example.backend_service.services;

import com.example.backend_service.models.*;
import com.example.backend_service.repositories.*;
import com.example.backend_service.services.i_service.I_ExamService;
import com.example.backend_service.services.i_service.I_Transaction_MarkExam;
import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamService implements I_ExamService, I_Transaction_MarkExam {

    private final BankTestJavaOopRepository bankTestJavaOopRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private final QuestionJavaCoreExamRepository questionJavaCoreExamRepository;

    private final ExamRepository examRepository;

    private final CourseRepository courseRepository;

    private final BankQuestionJavaCoreService bankQuestionJavaCoreService;

    private final BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository;

    private final MarkStudentRepository markStudentRepository;

    private final ExamJavaOopRepository examJavaOopRepository;

    public ExamService(QuestionJavaCoreExamRepository questionJavaCoreExamRepository, ExamRepository examRepository, CourseRepository courseRepository, BankQuestionJavaCoreService bankQuestionJavaCoreService, BankQuestionJavaCoreRepository bankQuestionJavaCoreRepository, MarkStudentRepository markStudentRepository, BankTestJavaOopRepository bankTestJavaOopRepository, ExamJavaOopRepository examJavaOopRepository) {
        this.questionJavaCoreExamRepository = questionJavaCoreExamRepository;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.bankQuestionJavaCoreService = bankQuestionJavaCoreService;
        this.bankQuestionJavaCoreRepository = bankQuestionJavaCoreRepository;
        this.markStudentRepository = markStudentRepository;
        this.bankTestJavaOopRepository = bankTestJavaOopRepository;
        this.examJavaOopRepository = examJavaOopRepository;
    }

    @Override
    public Exam createExam(Exam exam, Long courseID) throws JpaSystemException {
        // Retrieve the course using the provided courseID
        Course course = courseRepository.findCourseByCourseID(courseID);
        //neu java khác sẽ có kiểu khác
        String javaCore = "Java core";
        String javaClass = "Java class";
        if(exam.getTopicExam().equalsIgnoreCase(javaCore)) {
            List<Map<String, Object>> questionRandomJavaCore = bankQuestionJavaCoreService.getRandom_10_Questions_JavaCore();
            if (course != null && !questionRandomJavaCore.isEmpty()) {
                // Set initial properties for the exam
                course.getExams().add(exam);
                exam.setCourse(course);
                // Iterate over the list of random questions
                for (Map<String, Object> questionMap : questionRandomJavaCore) {
                    Long questionJavaCoreID = (Long) questionMap.get("questionJavaCoreID");
                    String contentQuestion = (String) questionMap.get("contentQuestion");
                    String codeSample = (String) questionMap.get("codeSample");
                    // Create the BankQuestionJavaCore instance for this specific question
                    BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
                    bankQuestionJavaCore.setQuestionJavaCoreID(questionJavaCoreID);

                    // Create the QuestionJavaCoreExam instance
                    QuestionJavaCoreExam questionJavaCoreExam = new QuestionJavaCoreExam();
                    questionJavaCoreExam.setContentQuestion(contentQuestion);
                    questionJavaCoreExam.setCodeSample(codeSample);
                    //set điểm
                    questionJavaCoreExam.setScore(1);

                    exam.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                    questionJavaCoreExam.setExam(exam);
                    questionJavaCoreExam.setBankQuestionJavaCore(bankQuestionJavaCore);

                    // Associate the question with its bank
                    bankQuestionJavaCore.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                }
                return examRepository.save(exam);
            }
            return null;
        } else if (exam.getTopicExam().equalsIgnoreCase(javaClass)) {

        }
        // Return null if course or questions are not found
        return null;
    }

    @Override
    public Exam
    createExam_JavaCore_With_ChooseQuestion(Exam exam,
                                            Long courseID,
                                            List<Map<String, Object>> questionJavaCoreExams
    ) throws JpaSystemException{
        Course course = courseRepository.findCourseByCourseID(courseID);
        if(course !=null && !questionJavaCoreExams.isEmpty()) {
            //set relationship
            course.getExams().add(exam);
            exam.setCourse(course);
            for (Map<String, Object> map : questionJavaCoreExams) {
                Integer questionJavaCoreIDInteger = (Integer) map.get("questionJavaCoreID");
                Long questionJavaCoreID = questionJavaCoreIDInteger != null ? questionJavaCoreIDInteger.longValue() : null;
                Object scoreObject = map.get("score");
                double score = 0.0;
                if (scoreObject instanceof Integer) {
                    score = (Integer) scoreObject;
                } else if (scoreObject instanceof Double) {
                    score = (Double) scoreObject;
                } else if (scoreObject instanceof String) {
                    try {
                        score = Double.parseDouble((String) scoreObject);
                    } catch (NumberFormatException e) {
                        // Xử lý lỗi nếu không thể chuyển đổi thành số
                        System.err.println("Invalid score format: " + scoreObject);
                    }
                }
                BankQuestionJavaCore bankQuestionJavaCoreFound =
                        bankQuestionJavaCoreRepository.findBankQuestionJavaCoreByQuestionJavaCoreID(questionJavaCoreID);
                if (bankQuestionJavaCoreFound != null) {
                    QuestionJavaCoreExam questionJavaCoreExam = new QuestionJavaCoreExam();
                    questionJavaCoreExam.setContentQuestion(bankQuestionJavaCoreFound.getContentQuestion());
                    questionJavaCoreExam.setCodeSample(bankQuestionJavaCoreFound.getCodeSample());
                    questionJavaCoreExam.setScore(score);
                    // relationship
                    exam.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                    questionJavaCoreExam.setExam(exam);
                    bankQuestionJavaCoreFound.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                    questionJavaCoreExam.setBankQuestionJavaCore(bankQuestionJavaCoreFound);
                }
            }
            return examRepository.save(exam);
        }
        return null;
    }

    @Override
    public Exam createExam_JavaCore_With_RandomQuestion(Exam exam, Long courseID, int numberOfQuestions)
            throws JpaSystemException {
        // Retrieve the course using the provided courseID
        Course course = courseRepository.findCourseByCourseID(courseID);
        //neu java khác sẽ có kiểu khác
        String javaCore = "Java Core";
        if(exam.getTopicExam().equalsIgnoreCase(javaCore)) {
            List<Map<String, Object>> questionRandomJavaCore = bankQuestionJavaCoreService
                    .getRandom_Questions_JavaCore_By_NumberQuestions(numberOfQuestions);
            if (course != null && !questionRandomJavaCore.isEmpty()) {
                // Set initial properties for the exam
                course.getExams().add(exam);
                exam.setCourse(course);
                // Iterate over the list of random questions
                for (Map<String, Object> questionMap : questionRandomJavaCore) {
                    Long questionJavaCoreID = (Long) questionMap.get("questionJavaCoreID");
                    String contentQuestion = (String) questionMap.get("contentQuestion");
                    String codeSample = (String) questionMap.get("codeSample");
                    // Create the BankQuestionJavaCore instance for this specific question
                    BankQuestionJavaCore bankQuestionJavaCore = new BankQuestionJavaCore();
                    bankQuestionJavaCore.setQuestionJavaCoreID(questionJavaCoreID);

                    // Create the QuestionJavaCoreExam instance
                    QuestionJavaCoreExam questionJavaCoreExam = new QuestionJavaCoreExam();
                    questionJavaCoreExam.setContentQuestion(contentQuestion);
                    questionJavaCoreExam.setCodeSample(codeSample);
                    //set điểm
                    double score = 10.0 / numberOfQuestions;
                    double roundedScore = Math.round(score * 4.0) / 4.0;
                    questionJavaCoreExam.setScore(roundedScore);

                    exam.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                    questionJavaCoreExam.setExam(exam);
                    questionJavaCoreExam.setBankQuestionJavaCore(bankQuestionJavaCore);

                    // Associate the question with its bank
                    bankQuestionJavaCore.getQuestionJavaCoreExams().add(questionJavaCoreExam);
                }
                return examRepository.save(exam);
            }
            return null;
        }
        // Return null if course or questions are not found
        return null;
    }

    @Override
    public Exam createExam_JavaClass_With_ChooseTest(Exam exam, Long courseID, Long bankTestJavaOopID) {
        Course courseFound = courseRepository.findCourseByCourseID(courseID);
        //neu java khác sẽ có kiểu khác
        String javaClass = "Java class";
        BankTestJavaOop bankTestJavaOopFound = bankTestJavaOopRepository
                .findBankTestJavaOopByBankTestJavaOopID(bankTestJavaOopID);
        if(bankTestJavaOopFound != null && courseFound != null && exam.getTopicExam().equalsIgnoreCase(javaClass)) {
            courseFound.getExams().add(exam);
            exam.setCourse(courseFound);

            ExamJavaOop examJavaOop = new ExamJavaOop();
            examJavaOop.setBankTestJavaOop(bankTestJavaOopFound);
            examJavaOop.setExam(exam);
            examJavaOop.setDateTimeSubmit(null);

            //map relationship
            bankTestJavaOopFound.getListExamJavaOop().add(examJavaOop);
            exam.setExamJavaOop(examJavaOop);
            examJavaOop.setExam(exam);
            //examJavaOopRepository.save(examJavaOop);
            return examRepository.save(exam);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getExamsByCourseID(Long courseID) throws JpaSystemException {
        List<Map<String, Object>> queryList = examRepository.getExamsByCourseID(courseID);
        List<Map<String, Object>> convertedList = new ArrayList<>();
        // Khởi tạo LocalDateTime now
        LocalDateTime now = LocalDateTime.now();

        if (!queryList.isEmpty()) {
            for (Map<String, Object> queryMap : queryList) {
                Map<String, Object> convertedMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    String convertedKey = ConvertNameField.convertSnakeToCamel(key);

                    if (!convertedKey.equals("examId")) {
                        convertedMap.put(convertedKey, value);
                    } else {
                        convertedMap.put("examID", value);
                    }
                }
                // Lấy startDate và endDate từ queryMap
                Timestamp startDateTimestamp = (Timestamp) queryMap.get("start_date"); // giả sử tên trường là "start_date"
                Timestamp endDateTimestamp = (Timestamp) queryMap.get("end_date"); // giả sử tên trường là "end_date"
                // Chuyển đổi Timestamp thành LocalDateTime
                LocalDateTime startDate = startDateTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime endDate = endDateTimestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                //xem them ktra
                //Integer examIDInt = (Integer) queryMap.get("exam_id");
                Long examID =  (Long) queryMap.get("exam_id");
                MarkStudent markStudent_DoneExam = examRepository.findMarkStudent_By_ExamID(examID);

                // Kiểm tra trạng thái
                // xét thêm bảng mark cho ra trạng thái complete
                if (now.isBefore(startDate)) {
                    convertedMap.put("status", "Locked");
                } else if (now.isAfter(endDate)) {
                    convertedMap.put("status", "Overdue");
                } else if(markStudent_DoneExam != null) {
                    convertedMap.put("status", "Completed");
                } else {
                    convertedMap.put("status", "Open");
                }
                convertedList.add(convertedMap);
            }
            return convertedList;
        }
        return null;
    }

    @Override
    public Map<String, Object> viewExam_By_ExamID(Long examID, Long courseID) throws JpaSystemException {
        List<Map<String, Object>> queryList = getExamsByCourseID(courseID);
        Optional<Map<String, Object>> exam = queryList.stream()
                .filter(queryMap -> examID.equals(queryMap.get("examID")))
                .findFirst();
        return exam.orElse(null);
    }

    @Override
    public Map<String, Object> view_Information_Exam_Before_Student(Long examID, Long courseID) throws JpaSystemException {
        Map<String, Object> viewExamMap = viewExam_By_ExamID(examID, courseID);
        if (viewExamMap != null) {
            String topicExam = (String) viewExamMap.get("topicExam");
            if (topicExam.equalsIgnoreCase("Java core")) {
                Integer numberOfQuestions = questionJavaCoreExamRepository.getTotalQuestionsByExamID(examID);
                viewExamMap.put("numberQuestions", numberOfQuestions);
            }
            return viewExamMap;
        }
        return null;
    }

    @Override
    @Transactional
    public Long deleteExam_By_ExamID(Long examID) throws JpaSystemException {
        Exam exam = examRepository.findExamByExamID(examID);
        if(exam != null) {
            MarkStudent markStudentFound_By_ExamID = markStudentRepository.
                    findMarkStudentByExam_ExamID(exam.getExamID());
            if(markStudentFound_By_ExamID != null) {
                //xoá phan diem thi nếu sv làm xong
                deleteMarkStudentID_By_MarkStudentID(markStudentFound_By_ExamID.getMarkStudentID());
            }
            //Xoa bai ktra neu la java core
            if(exam.getTopicExam().equalsIgnoreCase("Java core")) {
                entityManager.createNativeQuery("delete from question_java_core_exam where exam_id = :examID")
                        .setParameter("examID", exam.getExamID())
                        .executeUpdate();
            } else if (exam.getTopicExam().equalsIgnoreCase("Java class")) {
                entityManager.createNativeQuery("delete from exam_java_oop where exam_id = :examID")
                        .setParameter("examID", exam.getExamID())
                        .executeUpdate();
            }

            entityManager.createNativeQuery("delete from exam where exam_id = :examID")
                    .setParameter("examID", exam.getExamID())
                    .executeUpdate();
            return exam.getExamID();
        }
        return null;
    }

    @Override
    public Exam findExam_By_ExamID(Long examID) {
        return examRepository.findExamByExamID(examID);
    }

    @Override
    @Transactional
    public Exam updateExam_By_ExamID(Exam exam, Long examID) throws JpaSystemException{
        Exam examFound = findExam_By_ExamID(examID);
        if(examFound != null) {
            String sqlUpdate = "update exam \n" +
                    "set \n" +
                    "    title_exam = ?, \n" +
                    "    type_exam = ?, \n" +
                    "    topic_exam = ?, \n" +
                    "    retake_exam = ?, \n" +
                    "    scoring_method = ?, \n" +
                    "    duration = ?, \n" +
                    "    start_date = ?, \n" +
                    "    end_date = ?, \n" +
                    "    view_table = ?, \n" +
                    "    link_exam_paper = ?, \n" +
                    "    password_exam = ?\n" +
                    "where \n" +
                    "    exam_id = ?;";

            entityManager.createNativeQuery(sqlUpdate)
                    .setParameter(1, exam.getTitleExam())
                    .setParameter(2, exam.getTypeExam())
                    .setParameter(3, exam.getTopicExam())
                    .setParameter(4, exam.isRetakeExam())
                    .setParameter(5, exam.getScoringMethod())
                    .setParameter(6, exam.getDuration())
                    .setParameter(7, exam.getStartDate())
                    .setParameter(8, exam.getEndDate())
                    .setParameter(9, exam.isViewTable())
                    .setParameter(10, exam.getLinkExamPaper())
                    .setParameter(11, exam.getPasswordExam())
                    .setParameter(12, exam.getExamID())
                    .executeUpdate();
            return examFound;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getExams_Calendar_Lecture_By_StartDate(String lectureID,
                                                                            int yearStartDate,
                                                                            int monthStartDate,
                                                                            int dateStartDate) throws JpaSystemException {
        String yearStartDateString = Integer.toString(yearStartDate);
        String monthStartDateString = Integer.toString(monthStartDate);
        String dateStartDateString = Integer.toString(dateStartDate);
        String dateFormat = yearStartDateString + "-" + monthStartDateString + "-" + dateStartDateString;
        return examRepository.getExams_Calendar_Lecture_By_StartDate(lectureID,dateFormat).stream()
                .map(originalMap -> {
                    Map<String, Object> newMap = new HashMap<>();
                    newMap.put("examID", originalMap.get("exam_id"));
                    newMap.put("titleExam", originalMap.get("title_exam"));
                    newMap.put("typeExam", originalMap.get("type_exam"));
                    newMap.put("className", originalMap.get("class_name"));
                    return newMap;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> get_Status_Retake_And_Scoring_Method_By_ExamID(Long examID)
            throws JpaSystemException {
        Exam examFound = findExam_By_ExamID(examID);
        if(examFound !=null) {
            Map<String, Object> queryMap = examRepository
                    .get_Status_Retake_And_Scoring_Method_By_ExamID(examFound.getExamID());
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("examID", queryMap.get("exam_id"));
            returnMap.put("retakeExam", queryMap.get("retake_exam"));
            returnMap.put("scoringMethod", queryMap.get("scoring_method"));
            return returnMap;
        }
        return new HashMap<>();
    }

    @Override
    public String getTitle_Exam_By_CourseID(Long courseID, String titleExam)
            throws JpaSystemException {
        return examRepository.getTitle_Exam_By_CourseID(courseID, titleExam);
    }

    @Override
    public Boolean getViewTable_From_ExamID(Long examID)  throws JpaSystemException {
        Exam examFound = findExam_By_ExamID(examID);
        if(examFound != null) {
            return examFound.isViewTable();
        }
        return null;
    }

    @Override
    public List<String> getListTitleExam() {
        List<Exam> examList = examRepository.findAll();
        List<String> titleExamList = new ArrayList<>();
        if(!examList.isEmpty()) {
            titleExamList = examList.stream().map(Exam::getTitleExam).collect(Collectors.toUnmodifiableList());
        }
        return titleExamList;
    }

    @Override
    public List<Long> getListDetailMarkStudentID_By_MarkStudentID(Long markStudentID)
            throws JpaSystemException{
        return markStudentRepository.getListDetailMarkStudentID_By_MarkStudentID(markStudentID);
    }

    @Override
    public List<Long> getListResultQuestionJavaCoreID_By_MarkStudentID(Long markStudentID)
            throws JpaSystemException{
        return markStudentRepository.getListResultQuestionJavaCoreID_By_MarkStudentID(markStudentID);
    }

    @Override
    public List<Long> getListOutputDebugResultJavaCoreID_By_MarkStudentID(Long markStudentID)
            throws JpaSystemException{
        return markStudentRepository.getListOutputDebugResultJavaCoreID_By_MarkStudentID(markStudentID);
    }

    @Override
    @Transactional
    public Void deleteMarkStudentID_By_MarkStudentID(Long markStudentID)
            throws JpaSystemException{
        //get ds tu query sql
        List<Long> queryListOutputID = getListOutputDebugResultJavaCoreID_By_MarkStudentID(markStudentID);

        List<Long> queryListResultQuestionJavaCoreID = getListResultQuestionJavaCoreID_By_MarkStudentID(markStudentID);

        List<Long> queryListDetailMarkStudentID = getListDetailMarkStudentID_By_MarkStudentID(markStudentID);

        //Các bước xóa record.
        if(!queryListOutputID.isEmpty()) {
            for(Long id: queryListOutputID) {
                String sqlDeleteID_Output = "delete from output_debug_result_java_core where output_debug_java_core_id = ?";
                entityManager.createNativeQuery(sqlDeleteID_Output)
                        .setParameter(1, id)
                        .executeUpdate();
            }
        }

        if(!queryListResultQuestionJavaCoreID.isEmpty()) {
            for(Long id: queryListResultQuestionJavaCoreID) {
                String sqlDeleteID_Result = "delete from result_question_java_core where result_question_java_core_id = ?";
                entityManager.createNativeQuery(sqlDeleteID_Result)
                        .setParameter(1, id)
                        .executeUpdate();
            }
        }

        if(!queryListDetailMarkStudentID.isEmpty()) {
            for(Long id: queryListDetailMarkStudentID) {
                String sqlDeleteID_DetailMarkStudent
                        = "delete from detail_mark_student where detail_mark_student_id = ?";
                entityManager.createNativeQuery(sqlDeleteID_DetailMarkStudent)
                        .setParameter(1, id)
                        .executeUpdate();
            }
        }

        //find mark student
        MarkStudent markStudentFound = markStudentRepository.findMarkStudentByMarkStudentID(markStudentID);
        if(markStudentFound != null) {
            //delete mark student
            String sqlDeleteMarkStudent = "delete from mark_student where mark_student_id = ?";
            entityManager.createNativeQuery(sqlDeleteMarkStudent)
                    .setParameter(1, markStudentID)
                    .executeUpdate();
        }
        return null;
    }
}

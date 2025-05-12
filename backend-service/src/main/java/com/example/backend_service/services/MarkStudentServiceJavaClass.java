package com.example.backend_service.services;

import com.example.backend_service.models.*;
import com.example.backend_service.others.DoubleGetMap;
import com.example.backend_service.repositories.DetailMarkStudentRepository;
import com.example.backend_service.repositories.ExamRepository;
import com.example.backend_service.repositories.MarkStudentRepository;
import com.example.backend_service.repositories.StudentRepository;
import com.example.backend_service.services.i_service.I_MarkStudentServiceJavaClass;
import jakarta.transaction.Transactional;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MarkStudentServiceJavaClass implements I_MarkStudentServiceJavaClass {

    private final MarkStudentRepository markStudentRepository;

    private final DetailMarkStudentRepository detailMarkStudentRepository;

    private final StudentRepository studentRepository;

    private final ExamRepository examRepository;

    public MarkStudentServiceJavaClass(MarkStudentRepository markStudentRepository, DetailMarkStudentRepository detailMarkStudentRepository, StudentRepository studentRepository, ExamRepository examRepository) {
        this.markStudentRepository = markStudentRepository;
        this.detailMarkStudentRepository = detailMarkStudentRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }


    @Override
    public DetailMarkStudent createMarkStudent_And_DetailMarkStudent(Map<String, Object> dataPut) {
        String studentID = (String) dataPut.get("studentID");
        Integer examIDInt = (Integer) dataPut.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        if(studentFound != null && examFound !=null ) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);
            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);

            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);

            DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
            detailMarkStudent.setDetailMarkExam(0);
            //date time submit
            detailMarkStudent.setDateSubmitted(LocalDateTime.now());
            //save detailMarkStudent submit coding

            //set relationship
            markStudent.getDetailMarkStudents().add(detailMarkStudent);
            detailMarkStudent.setMarkStudent(markStudent);

            markStudentRepository.save(markStudent);
            return detailMarkStudent;
        }
        return null;
    }

    @Override
    public MarkStudent updateMarkStudent_Zero_If_Student_No_Submit_First(Map<String, Object> dataPut)
            throws JpaSystemException {
        String studentID = (String) dataPut.get("studentID");
        Integer examIDInt = (Integer) dataPut.get("examID");
        Long examID = examIDInt != null ? examIDInt.longValue() : null;
        Student studentFound = studentRepository.findStudentByStudentID(studentID);
        Exam examFound = examRepository.findExamByExamID(examID);
        if(studentFound != null && examFound !=null ) {
            MarkStudent markStudent = new MarkStudent();
            //set attribute
            //set 0d, xem truong hop sv qua han
            markStudent.setMarkExam(0);
            //set relationship
            studentFound.getMarkStudentList().add(markStudent);
            markStudent.setStudent(studentFound);

            examFound.setMarkStudent(markStudent);
            markStudent.setExam(examFound);

            DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
            detailMarkStudent.setDetailMarkExam(0);
            //date time submit
            detailMarkStudent.setDateSubmitted(LocalDateTime.now());

            //set relationship
            markStudent.getDetailMarkStudents().add(detailMarkStudent);
            detailMarkStudent.setMarkStudent(markStudent);

            return markStudentRepository.save(markStudent);
        }
        return null;
    }

    @Override
    public DetailMarkStudent updateMarkStudent_Zero_If_Student_No_Submit_Retake
            (Map<String, Object> dataPut, String scoringMethod, MarkStudent markStudentFound) throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
        detailMarkStudent.setDetailMarkExam(0);
        //date time submit
        detailMarkStudent.setDateSubmitted(LocalDateTime.now());

        //set relationship
        markStudentFound.getDetailMarkStudents().add(detailMarkStudent);
        detailMarkStudent.setMarkStudent(markStudentFound);

        if(scoringMethod.equalsIgnoreCase("Max")) {
            //detailMarkStudent.getMarkStudent().setMarkExam(detailMarkStudent.getDetailMarkExam());
            double mark = detailMarkStudentRepository
                    .getMaxDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());

            detailMarkStudentRepository.save(detailMarkStudent);
            detailMarkStudent.getMarkStudent().setMarkExam(mark);
            markStudentRepository.save(detailMarkStudent.getMarkStudent());
        } else if(scoringMethod.equalsIgnoreCase("Average")) {
            double mark = detailMarkStudentRepository
                    .getAvgDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());
            //System.out.println("Mark average: " + mark);
            detailMarkStudentRepository.save(detailMarkStudent);
            detailMarkStudent.getMarkStudent().setMarkExam(Math.round(mark * 10.0) / 10.0);
            markStudentRepository.save(detailMarkStudent.getMarkStudent());
        } else if (scoringMethod.equalsIgnoreCase("Last exam")) {
            double mark = detailMarkStudentRepository
                    .getLastSubmittedDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());

            detailMarkStudentRepository.save(detailMarkStudent);
            detailMarkStudent.getMarkStudent().setMarkExam(mark);
            markStudentRepository.save(detailMarkStudent.getMarkStudent());
        }
        return detailMarkStudent;
    }

    @Override
    @Transactional
    public MarkStudent saveResultJavaClassFirst(Map<String, Object> dataPost)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = createMarkStudent_And_DetailMarkStudent(dataPost);
        if(detailMarkStudent != null) {
            List<Map<String, Object>> detailAnswers = (List<Map<String, Object>>) dataPost.get("detailAnswers");
            System.out.println(detailAnswers);
            if(!detailAnswers.isEmpty()) {
                for(Map<String, Object> deMap: detailAnswers) {
                    DetailAnswerJavaClass detailAnswerJavaClass = new DetailAnswerJavaClass();
                    detailAnswerJavaClass.setSentence((String) deMap.get("sentence"));
                    detailAnswerJavaClass.setReviews((String) deMap.get("reviews"));
                    //scoreAchievement
                    detailAnswerJavaClass.setScoreAchievement(DoubleGetMap.getDoubleValue(deMap, "scoreAchievement"));
                    detailAnswerJavaClass.setMaxScore(DoubleGetMap.getDoubleValue(deMap, "maxScore"));
                    detailMarkStudent.getDetailAnswerJavaClassList().add(detailAnswerJavaClass);
                    detailAnswerJavaClass.setDetailMarkStudent(detailMarkStudent);
                    //set cho detailMarkStudent
                    detailAnswerJavaClass.getDetailMarkStudent()
                            .setDetailMarkExam((Double) dataPost.get("totalScore"));
                }
                //set cho mark student
                //luu y persist detailMarkStudent -> set cho mark student -> persist markStudent
                detailMarkStudentRepository.save(detailMarkStudent);
                detailMarkStudent.getMarkStudent().setMarkExam(DoubleGetMap.getDoubleValue(dataPost, "totalScore"));
                markStudentRepository.save(detailMarkStudent.getMarkStudent());
                return detailMarkStudent.getMarkStudent();
            }
        }
        return null;
    }

    @Override
    public DetailMarkStudent saveResultJavaClassRetake(Map<String, Object> dataPost,
                                                       MarkStudent markStudentFound,
                                                       String scoringMethod)
            throws JpaSystemException {
        DetailMarkStudent detailMarkStudent = new DetailMarkStudent();
        detailMarkStudent.setDetailMarkExam(DoubleGetMap.getDoubleValue(dataPost, "totalScore"));
        //date time submit
        detailMarkStudent.setDateSubmitted(LocalDateTime.now());
        //save detailMarkStudent submit coding
        
        //set relationship
        markStudentFound.getDetailMarkStudents().add(detailMarkStudent);
        detailMarkStudent.setMarkStudent(markStudentFound);
        List<Map<String, Object>> detailAnswers = (List<Map<String, Object>>) dataPost.get("detailAnswers");
        if(!detailAnswers.isEmpty()) {
            for(Map<String, Object> deMap: detailAnswers) {
                DetailAnswerJavaClass detailAnswerJavaClass = new DetailAnswerJavaClass();
                detailAnswerJavaClass.setSentence((String) deMap.get("sentence"));
                detailAnswerJavaClass.setReviews((String) deMap.get("reviews"));
                System.out.println("score achievement: "+DoubleGetMap.getDoubleValue(deMap, "scoreAchievement"));
                detailAnswerJavaClass.setScoreAchievement(DoubleGetMap.getDoubleValue(deMap, "scoreAchievement"));
                detailAnswerJavaClass.setMaxScore(DoubleGetMap.getDoubleValue(deMap, "maxScore"));
                detailMarkStudent.getDetailAnswerJavaClassList().add(detailAnswerJavaClass);
                detailAnswerJavaClass.setDetailMarkStudent(detailMarkStudent);
                //set cho detailMarkStudent
//                detailAnswerJavaClass.getDetailMarkStudent()
//                        .setDetailMarkExam(Double.parseDouble((String) dataPost.get("totalScore")));
            }
            //set cho mark student
            if(scoringMethod.equalsIgnoreCase("Max")) {
                //detailMarkStudent.getMarkStudent().setMarkExam(detailMarkStudent.getDetailMarkExam());
                double mark = detailMarkStudentRepository
                        .getMaxDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());
                detailMarkStudentRepository.save(detailMarkStudent);
                detailMarkStudent.getMarkStudent().setMarkExam(mark);
                markStudentRepository.save(detailMarkStudent.getMarkStudent());
            } else if(scoringMethod.equalsIgnoreCase("Average")) {
                double mark = detailMarkStudentRepository
                        .getAvgDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());
                //System.out.println("Mark average: " + mark);
                detailMarkStudentRepository.save(detailMarkStudent);
                detailMarkStudent.getMarkStudent().setMarkExam(Math.round(mark * 10.0) / 10.0);
                markStudentRepository.save(detailMarkStudent.getMarkStudent());
            } else if (scoringMethod.equalsIgnoreCase("Last exam")) {
                double mark = detailMarkStudentRepository
                        .getLastSubmittedDetailMarkExam_By_MarkStudentID(markStudentFound.getMarkStudentID());

                detailMarkStudentRepository.save(detailMarkStudent);
                detailMarkStudent.getMarkStudent().setMarkExam(mark);
                markStudentRepository.save(detailMarkStudent.getMarkStudent());
            }
            return detailMarkStudent;
        }
        return null;
    }

}

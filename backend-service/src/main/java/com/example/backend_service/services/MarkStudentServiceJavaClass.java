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

            //examFound.getMarkStudents().add(markStudent);
            markStudent.setExamID(examFound.getExamID());

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

            //examFound.getMarkStudents().add(markStudent);
            markStudent.setExamID(examFound.getExamID());

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
            //save code java class submit
            //get fields
            String suggest = (String) dataPost.get("suggest");
            String codeSubmitString = (String) dataPost.get("codeSubmitString");

            CodeSubmitJavaClass codeSubmitJavaClass = new CodeSubmitJavaClass();

            codeSubmitJavaClass.setSuggest(suggest);
            codeSubmitJavaClass.setCodeSubmitString(codeSubmitString);

            //set relationship
            detailMarkStudent.setCodeSubmitJavaClass(codeSubmitJavaClass);
            codeSubmitJavaClass.setDetailMarkStudent(detailMarkStudent);

            List<Map<String, Object>> detailAnswers = (List<Map<String, Object>>) dataPost.get("detail");
            //System.out.println("Detail: " + detailAnswers);
            //System.out.println(detailAnswers);
            double totalScore = ((Number) dataPost.get("totalScore")).doubleValue();
            if(totalScore > 10) {
                totalScore = 10;
            }
            if(!detailAnswers.isEmpty()) {
                for(Map<String, Object> deMap: detailAnswers) {
                    DetailAnswerJavaClass detailAnswerJavaClass = new DetailAnswerJavaClass();
                    detailAnswerJavaClass.setSentence((String) deMap.get("sentence"));
                    detailAnswerJavaClass.setReviews((String) deMap.get("reviews"));
                    //scoreAchievement
                    //double doubleScoreAchieve = (Double) dataPost.get("scoreAchieve");
                    //System.out.println("double score achieve: "+DoubleGetMap.getDoubleValue(dataPost, "scoreAchieve"));
                    detailAnswerJavaClass.setScoreAchievement(((Number) deMap.get("scoreAchieve")).doubleValue());
                    detailAnswerJavaClass.setMaxScore(((Number) deMap.get("maxScore")).doubleValue());
                    detailMarkStudent.getDetailAnswerJavaClassList().add(detailAnswerJavaClass);
                    detailAnswerJavaClass.setDetailMarkStudent(detailMarkStudent);
                    //set cho detailMarkStudent
                    detailAnswerJavaClass.getDetailMarkStudent()
                            .setDetailMarkExam(totalScore);
                }
                //set cho mark student
                //luu y persist detailMarkStudent -> set cho mark student -> persist markStudent
                detailMarkStudentRepository.save(detailMarkStudent);
                detailMarkStudent.getMarkStudent().setMarkExam(totalScore);
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
        double totalScore = ((Number) dataPost.get("totalScore")).doubleValue();
        if(totalScore > 10) {
            totalScore = 10;
        }
        detailMarkStudent.setDetailMarkExam(totalScore);
        //date time submit
        detailMarkStudent.setDateSubmitted(LocalDateTime.now());
        //save detailMarkStudent submit coding
        
        //set relationship
        markStudentFound.getDetailMarkStudents().add(detailMarkStudent);
        detailMarkStudent.setMarkStudent(markStudentFound);
        //save codeSubmitString

        String suggest = (String) dataPost.get("suggest");
        String codeSubmitString = (String) dataPost.get("codeSubmitString");

        CodeSubmitJavaClass codeSubmitJavaClass = new CodeSubmitJavaClass();

        codeSubmitJavaClass.setSuggest(suggest);
        codeSubmitJavaClass.setCodeSubmitString(codeSubmitString);

        //set relationship
        detailMarkStudent.setCodeSubmitJavaClass(codeSubmitJavaClass);
        codeSubmitJavaClass.setDetailMarkStudent(detailMarkStudent);

        List<Map<String, Object>> detailAnswers = (List<Map<String, Object>>) dataPost.get("detail");
        if(!detailAnswers.isEmpty()) {
            for(Map<String, Object> deMap: detailAnswers) {
                DetailAnswerJavaClass detailAnswerJavaClass = new DetailAnswerJavaClass();
                detailAnswerJavaClass.setSentence((String) deMap.get("sentence"));
                detailAnswerJavaClass.setReviews((String) deMap.get("reviews"));
                //System.out.println("score achievement: "+DoubleGetMap.getDoubleValue(deMap, "scoreAchieve"));
                detailAnswerJavaClass.setScoreAchievement(((Number) deMap.get("scoreAchieve")).doubleValue());
                detailAnswerJavaClass.setMaxScore(((Number) deMap.get("maxScore")).doubleValue());
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
                System.out.println("Max mark: "+ mark);
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

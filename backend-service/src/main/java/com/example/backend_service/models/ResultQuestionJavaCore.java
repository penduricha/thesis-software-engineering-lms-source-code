package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultQuestionJavaCore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_question_java_core_id", nullable = false)
    private Long resultQuestionJavaCoreID;

//    @Column(nullable = false)
//    private Long questionJavaCoreID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeStudentSubmitted;

    @Column(columnDefinition = "longtext")
    private String outputCodeStudent;


    @ManyToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;

    @OneToOne
    @JoinColumn(name = "question_java_core_exam_id")
    @JsonIgnore
    private QuestionJavaCoreExam questionJavaCoreExam;

    public Long getResultQuestionJavaCoreID() {
        return resultQuestionJavaCoreID;
    }

    public void setResultQuestionJavaCoreID(Long resultQuestionJavaCoreID) {
        this.resultQuestionJavaCoreID = resultQuestionJavaCoreID;
    }

    public String getCodeStudentSubmitted() {
        return codeStudentSubmitted;
    }

    public void setCodeStudentSubmitted(String codeStudentSubmitted) {
        this.codeStudentSubmitted = codeStudentSubmitted;
    }

    public String getOutputCodeStudent() {
        return outputCodeStudent;
    }

    public void setOutputCodeStudent(String outputCodeStudent) {
        this.outputCodeStudent = outputCodeStudent;
    }

    public DetailMarkStudent getDetailMarkStudent() {
        return detailMarkStudent;
    }

    public void setDetailMarkStudent(DetailMarkStudent detailMarkStudent) {
        this.detailMarkStudent = detailMarkStudent;
    }

    public QuestionJavaCoreExam getQuestionJavaCoreExam() {
        return questionJavaCoreExam;
    }

    public void setQuestionJavaCoreExam(QuestionJavaCoreExam questionJavaCoreExam) {
        this.questionJavaCoreExam = questionJavaCoreExam;
    }
}

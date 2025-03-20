package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionJavaCoreExam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_java_core_exam_id", nullable = false)
    private Long questionJavaCoreExamID;

//    @Column(name = "question_java_core_id", nullable = false)
//    private Long questionJavaCoreID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contentQuestion;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeSample;

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private boolean isMarkedFlag;

    public boolean isMarkedFlag() {
        return isMarkedFlag;
    }

    public void setMarkedFlag(boolean markedFlag) {
        isMarkedFlag = markedFlag;
    }

    @PrePersist
    protected void onCreated(){
        isMarkedFlag = false;
    }

//    @PreUpdate
//    protected void onUpdated(){
//        updateAt = LocalDateTime.now();
//    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    //map với exam
    @ManyToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "question_java_core_id")
    @JsonIgnore
    private BankQuestionJavaCore bankQuestionJavaCore;

    @OneToOne(mappedBy = "questionJavaCoreExam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private ResultQuestionJavaCore resultQuestionJavaCore;

//    public QuestionJavaCoreExam(String contentQuestion, String codeSample) {
//        this.contentQuestion = contentQuestion;
//        this.codeSample = codeSample;
//    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public BankQuestionJavaCore getBankQuestionJavaCore() {
        return bankQuestionJavaCore;
    }

    public void setBankQuestionJavaCore(BankQuestionJavaCore bankQuestionJavaCore) {
        this.bankQuestionJavaCore = bankQuestionJavaCore;
    }

    public ResultQuestionJavaCore getResultQuestionJavaCore() {
        return resultQuestionJavaCore;
    }

    public void setResultQuestionJavaCore(ResultQuestionJavaCore resultQuestionJavaCore) {
        this.resultQuestionJavaCore = resultQuestionJavaCore;
    }

//    public QuestionJavaCoreExam(Long questionJavaCoreExamID, String contentQuestion, String codeSample) {
//        this.questionJavaCoreExamID = questionJavaCoreExamID;
//        this.contentQuestion = contentQuestion;
//        this.codeSample = codeSample;
//    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }

    public String getCodeSample() {
        return codeSample;
    }

    public void setCodeSample(String codeSample) {
        this.codeSample = codeSample;
    }

    public Long getQuestionJavaCoreExamID() {
        return questionJavaCoreExamID;
    }

    public void setQuestionJavaCoreExamID(Long questionJavaCoreExamID) {
        this.questionJavaCoreExamID = questionJavaCoreExamID;
    }
}

package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeStudentSubmitted;

    private double markAchieve;


    public double getMarkAchieve() {
        return markAchieve;
    }

    public void setMarkAchieve(double markAchieve) {
        this.markAchieve = markAchieve;
    }

    @OneToMany(mappedBy = "resultQuestionJavaCore", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<OutputDebugResultJavaCore> outputDebugResultJavaCores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;

    @Column(name = "question_java_core_exam_id", nullable = false)
    private Long questionJavaCoreExamID;

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


    public DetailMarkStudent getDetailMarkStudent() {
        return detailMarkStudent;
    }

    public void setDetailMarkStudent(DetailMarkStudent detailMarkStudent) {
        this.detailMarkStudent = detailMarkStudent;
    }

    public Long getQuestionJavaCoreExamID() {
        return questionJavaCoreExamID;
    }

    public void setQuestionJavaCoreExamID(Long questionJavaCoreExamID) {
        this.questionJavaCoreExamID = questionJavaCoreExamID;
    }

    public List<OutputDebugResultJavaCore> getOutputDebugResultJavaCores() {
        return outputDebugResultJavaCores;
    }

    public void setOutputDebugResultJavaCores(List<OutputDebugResultJavaCore> outputDebugResultJavaCores) {
        this.outputDebugResultJavaCores = outputDebugResultJavaCores;
    }
}

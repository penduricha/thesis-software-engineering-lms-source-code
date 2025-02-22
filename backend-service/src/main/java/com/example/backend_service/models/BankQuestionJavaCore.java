package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankQuestionJavaCore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_java_core_id", nullable = false)
    private Long questionJavaCoreID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contentQuestion;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeSample;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeRunToOutput;

    //mapping
    @OneToMany(mappedBy = "bankQuestionJavaCore", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BankTestCaseJavaCore> bankTestCaseJavaCores = new ArrayList<>();

    @OneToMany(mappedBy = "bankQuestionJavaCore", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<QuestionJavaCoreExam> questionJavaCoreExams = new ArrayList<>();

    public Long getQuestionJavaCoreID() {
        return questionJavaCoreID;
    }

    public void setQuestionJavaCoreID(Long questionJavaCoreID) {
        this.questionJavaCoreID = questionJavaCoreID;
    }

    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams() {
        return questionJavaCoreExams;
    }

    public void setQuestionJavaCoreExams(List<QuestionJavaCoreExam> questionJavaCoreExams) {
        this.questionJavaCoreExams = questionJavaCoreExams;
    }
}

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

    @Column(nullable = false, columnDefinition = "longtext", unique = true)
    private String contentQuestion;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeSample;

    @Column(nullable = true, columnDefinition = "longtext")
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

    public String getCodeRunToOutput() {
        return codeRunToOutput;
    }

    public void setCodeRunToOutput(String codeRunToOutput) {
        this.codeRunToOutput = codeRunToOutput;
    }

    public List<BankTestCaseJavaCore> getBankTestCaseJavaCores() {
        return bankTestCaseJavaCores;
    }

    public void setBankTestCaseJavaCores(List<BankTestCaseJavaCore> bankTestCaseJavaCores) {
        this.bankTestCaseJavaCores = bankTestCaseJavaCores;
    }
}

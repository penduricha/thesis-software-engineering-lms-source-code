package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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

    @Column(name = "question_java_core_id", nullable = false)
    private Long questionJavaCoreID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contentQuestion;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeSample;

    //map với exam
    @ManyToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private Exam exam;

}

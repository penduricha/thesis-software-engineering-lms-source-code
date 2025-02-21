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

    @Column(nullable = false)
    private Long questionJavaCoreID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String codeStudentSubmitted;

    private double score;

    @ManyToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;
}

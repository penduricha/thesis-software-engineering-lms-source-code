package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MarkStudent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_student_id", nullable = false)
    private Long markStudentID;

    private double markExam;

    //map with student;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @OneToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private Exam exam;

    @OneToMany(mappedBy = "markStudent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<DetailMarkStudent> detailMarkStudents = new ArrayList<>();
}

package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="exam_id")
    private Long examID;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String titleExam;

    @Column(nullable = false, columnDefinition = "nvarchar(10)")
    private String typeExam;

    @Column(nullable = false, columnDefinition = "nvarchar(20)")
    private String topicExam;

    @Column(nullable = false)
    private boolean retakeExam;

    @Column(nullable = false, columnDefinition = "nvarchar(10)")
    private String scoringMethod;

    @Column(columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Column(columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @Column(columnDefinition = "nvarchar(50)")
    private String linkExamPaper;

    @Column(columnDefinition = "nvarchar(130)")
    private String passwordExam;

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
    }

    //mapping course
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<QuestionJavaCoreExam> questionJavaCoreExams = new ArrayList<>();

    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private MarkStudent markStudent;
}

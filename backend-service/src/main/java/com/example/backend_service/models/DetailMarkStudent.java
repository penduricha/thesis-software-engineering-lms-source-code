package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailMarkStudent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="detail_mark_student_id")
    private Long detailMarkStudentID;

    private double detailMarkExam;

    //map n-1 mark student
    @ManyToOne
    @JoinColumn(name = "mark_student_id")
    @JsonIgnore
    private MarkStudent markStudent;

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateSubmitted;

    @PrePersist
    protected void onCreated(){
        dateSubmitted = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "detailMarkStudent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ResultQuestionJavaCore> resultQuestionJavaCoreList = new ArrayList<>();
}

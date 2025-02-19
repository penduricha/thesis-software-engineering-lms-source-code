package com.example.backend_service.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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

    private String titleExam;

    private String typeExam;
}

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
public class CodeSubmitJavaClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_submit_java_class_id", nullable = false)
    private Long codeSubmitJavaClassID;

    @Column(columnDefinition = "longtext")
    private String codeSubmitString;

    //map 1 - 1 voi detail mark student
    @OneToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;
}

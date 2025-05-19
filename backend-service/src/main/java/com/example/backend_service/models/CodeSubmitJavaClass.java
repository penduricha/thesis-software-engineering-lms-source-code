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

    public Long getCodeSubmitJavaClassID() {
        return codeSubmitJavaClassID;
    }

    public void setCodeSubmitJavaClassID(Long codeSubmitJavaClassID) {
        this.codeSubmitJavaClassID = codeSubmitJavaClassID;
    }

    public String getCodeSubmitString() {
        return codeSubmitString;
    }

    public void setCodeSubmitString(String codeSubmitString) {
        this.codeSubmitString = codeSubmitString;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public DetailMarkStudent getDetailMarkStudent() {
        return detailMarkStudent;
    }

    public void setDetailMarkStudent(DetailMarkStudent detailMarkStudent) {
        this.detailMarkStudent = detailMarkStudent;
    }

    @Column(columnDefinition = "longtext")
    private String codeSubmitString;

    @Column(columnDefinition = "longtext")
    private String suggest;

    //map 1 - 1 voi detail mark student
    @OneToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;
}

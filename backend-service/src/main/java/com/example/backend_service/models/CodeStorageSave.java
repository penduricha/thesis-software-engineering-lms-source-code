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
public class CodeStorageSave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "code_storage_save_id")
    private Long codeStorageSaveID;

    @Column(columnDefinition = "longtext")
    private String codeSave;

    @Column(nullable = false)
    private int indexQuestionSave;

    //map with
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Long getCodeStorageSaveID() {
        return codeStorageSaveID;
    }

    public void setCodeStorageSaveID(Long codeStorageSaveID) {
        this.codeStorageSaveID = codeStorageSaveID;
    }

    public String getCodeSave() {
        return codeSave;
    }

    public void setCodeSave(String codeSave) {
        this.codeSave = codeSave;
    }

    public int getIndexQuestionSave() {
        return indexQuestionSave;
    }

    public void setIndexQuestionSave(int indexQuestionSave) {
        this.indexQuestionSave = indexQuestionSave;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

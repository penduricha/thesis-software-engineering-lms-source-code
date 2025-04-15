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
@AllArgsConstructor
public class BankTestJavaOop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_test_java_oop_id", nullable = false)
    private Long bankTestJavaOopID;

//    @Override
//    public String toString() {
//        return "BankTestJavaOop{" +
//                "imageDiagram='" + imageDiagram + '\'' +
//                ", descriptionTest='" + descriptionTest + '\'' +
//                ", nameTest='" + nameTest + '\'' +
//                ", bankTestJavaOopID=" + bankTestJavaOopID +
//                '}';
//    }

    @Column(nullable = false, columnDefinition = "nvarchar(30)", unique = true)
    private String nameTest;

    @Column(nullable = false, columnDefinition = "longtext")
    private String descriptionTest;

    @Column(columnDefinition = "nvarchar(100)")
    private String imageDiagram;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateAt;

    public List<ExamJavaOop> getListExamJavaOop() {
        return listExamJavaOop;
    }

    public void setListExamJavaOop(List<ExamJavaOop> listExamJavaOop) {
        this.listExamJavaOop = listExamJavaOop;
    }

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        updateAt = LocalDateTime.now();
    }

    //map class
    @OneToMany(mappedBy = "bankTestJavaOop", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<ExamJavaOop> listExamJavaOop = new ArrayList<>();

//    public BankTestJavaOop(String nameTest, String descriptionTest, String imageDiagram) {
//        this.nameTest = nameTest;
//        this.descriptionTest = descriptionTest;
//        this.imageDiagram = imageDiagram;
//    }

    public BankTestJavaOop() {
    }

    public Long getBankTestJavaOopID() {
        return bankTestJavaOopID;
    }

    public void setBankTestJavaOopID(Long bankTestJavaOopID) {
        this.bankTestJavaOopID = bankTestJavaOopID;
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public String getDescriptionTest() {
        return descriptionTest;
    }

    public void setDescriptionTest(String descriptionTest) {
        this.descriptionTest = descriptionTest;
    }

    public String getImageDiagram() {
        return imageDiagram;
    }

    public void setImageDiagram(String imageDiagram) {
        this.imageDiagram = imageDiagram;
    }
}

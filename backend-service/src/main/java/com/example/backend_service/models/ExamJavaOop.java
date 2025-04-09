package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@ToString
@AllArgsConstructor
public class ExamJavaOop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_java_oop_id", nullable = false)
    private Long examJavaOopID;

    //thời gian tính từ lúc
    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTimeSubmit;

    @OneToOne
    @JoinColumn(name = "exam_id")
    @JsonIgnore
    private Exam exam;

    @OneToOne
    @JoinColumn(name = "bank_test_java_oop_id")
    @JsonIgnore
    private BankTestJavaOop bankTestJavaOop;

    public ExamJavaOop() {

    }

    public Long getExamJavaOopID() {
        return examJavaOopID;
    }

    public void setExamJavaOopID(Long examJavaOopID) {
        this.examJavaOopID = examJavaOopID;
    }

    public LocalDateTime getDateTimeSubmit() {
        return dateTimeSubmit;
    }

    public void setDateTimeSubmit(LocalDateTime dateTimeSubmit) {
        this.dateTimeSubmit = dateTimeSubmit;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public BankTestJavaOop getBankTestJavaOop() {
        return bankTestJavaOop;
    }

    public void setBankTestJavaOop(BankTestJavaOop bankTestJavaOop) {
        this.bankTestJavaOop = bankTestJavaOop;
    }
}

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

    //orphanRemoval = true Điều này đảm bảo rằng nếu một không còn liên kết với
    @OneToMany(mappedBy = "markStudent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<DetailMarkStudent> detailMarkStudents = new ArrayList<>();

    public MarkStudent() {
    }

    public Long getMarkStudentID() {
        return markStudentID;
    }

    public void setMarkStudentID(Long markStudentID) {
        this.markStudentID = markStudentID;
    }

    public double getMarkExam() {
        return markExam;
    }

    public void setMarkExam(double markExam) {
        this.markExam = markExam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<DetailMarkStudent> getDetailMarkStudents() {
        return detailMarkStudents;
    }

    public void setDetailMarkStudents(List<DetailMarkStudent> detailMarkStudents) {
        this.detailMarkStudents = detailMarkStudents;
    }
}

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

    @Column(nullable = true, columnDefinition = "nvarchar(10)")
    private String scoringMethod;

    @Column(nullable = false)
    private int duration;

    @Column(columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;

    @Column(columnDefinition = "datetime", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;

    @Column(columnDefinition = "nvarchar(50)")
    private String linkExamPaper;

    @Column(columnDefinition = "nvarchar(130)")
    private String passwordExam;

    @Column(nullable = false)
    private boolean studentAccess;

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "datetime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        updateAt = LocalDateTime.now();
    }

    //mapping course
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private List<QuestionJavaCoreExam> questionJavaCoreExams = new ArrayList<>();

    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private MarkStudent markStudent;

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudentAccess(boolean studentAccess) {
        this.studentAccess = studentAccess;
    }

    public List<QuestionJavaCoreExam> getQuestionJavaCoreExams() {
        return questionJavaCoreExams;
    }

    public String getTitleExam() {
        return titleExam;
    }

    public void setTitleExam(String titleExam) {
        this.titleExam = titleExam;
    }

    public String getTypeExam() {
        return typeExam;
    }

    public void setTypeExam(String typeExam) {
        this.typeExam = typeExam;
    }

    public String getTopicExam() {
        return topicExam;
    }

    public void setTopicExam(String topicExam) {
        this.topicExam = topicExam;
    }

    public boolean isRetakeExam() {
        return retakeExam;
    }

    public void setRetakeExam(boolean retakeExam) {
        this.retakeExam = retakeExam;
    }

    public String getScoringMethod() {
        return scoringMethod;
    }

    public void setScoringMethod(String scoringMethod) {
        this.scoringMethod = scoringMethod;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getLinkExamPaper() {
        return linkExamPaper;
    }

    public void setLinkExamPaper(String linkExamPaper) {
        this.linkExamPaper = linkExamPaper;
    }

    public String getPasswordExam() {
        return passwordExam;
    }

    public void setPasswordExam(String passwordExam) {
        this.passwordExam = passwordExam;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }
}

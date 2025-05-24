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

    public void setQuestionJavaCoreExams(List<QuestionJavaCoreExam> questionJavaCoreExams) {
        this.questionJavaCoreExams = questionJavaCoreExams;
    }



    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String titleExam;

    @Column(nullable = false, columnDefinition = "nvarchar(10)")
    private String typeExam;

    @Column(nullable = false, columnDefinition = "nvarchar(20)")
    private String topicExam;

    public boolean isViewTable() {
        return viewTable;
    }

    public void setViewTable(boolean viewTable) {
        this.viewTable = viewTable;
    }

    private boolean viewTable;

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

    public ExamJavaOop getExamJavaOop() {
        return examJavaOop;
    }

    public void setExamJavaOop(ExamJavaOop examJavaOop) {
        this.examJavaOop = examJavaOop;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(columnDefinition = "nvarchar(50)")
    private String linkExamPaper;

    @Column(columnDefinition = "nvarchar(130)")
    private String passwordExam;


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

//    public List<MarkStudent> getMarkStudents() {
//        return markStudents;
//    }
//
//    public void setMarkStudents(List<MarkStudent> markStudents) {
//        this.markStudents = markStudents;
//    }
//
//    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JsonIgnore
//    private List<MarkStudent> markStudents = new ArrayList<>();

    @OneToOne(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private ExamJavaOop examJavaOop;

    //map with student
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "exams",
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    public void setCourse(Course course) {
        this.course = course;
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

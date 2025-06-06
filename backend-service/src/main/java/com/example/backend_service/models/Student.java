package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Id
    @Column(nullable = false, columnDefinition = "nvarchar(8)", name ="student_id")
    private String studentID;

    @Column(nullable = false, columnDefinition = "nvarchar(40)")
    private String lastName;

    @Column(nullable = false, columnDefinition = "nvarchar(10)")
//    @ColumnDefault("")
    private String firstName;

    @Column(nullable = false)
    public boolean gender;

    @Getter
    @Column(nullable = false, columnDefinition = "nvarchar(130)")
    private String password;

    @Getter
    @Column(columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "datetime", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateTimeStartExam;

//    private Instant createAt;

    public List<MarkStudent> getMarkStudentList() {
        return markStudentList;
    }

    public void setMarkStudentList(List<MarkStudent> markStudentList) {
        this.markStudentList = markStudentList;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public LocalDateTime getDateTimeStartExam() {
        return dateTimeStartExam;
    }

    public void setDateTimeStartExam(LocalDateTime dateTimeStartExam) {
        this.dateTimeStartExam = dateTimeStartExam;
    }

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<MarkStudent> markStudentList = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CodeStorageSave> codeStorageSaves = new ArrayList<>();

    //map with exam
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_access_exam",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    @JsonIgnore
    private List<Exam> exams = new ArrayList<>();

    public String getStudentID() {
        return studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<CodeStorageSave> getCodeStorageSaves() {
        return codeStorageSaves;
    }

    public void setCodeStorageSaves(List<CodeStorageSave> codeStorageSaves) {
        this.codeStorageSaves = codeStorageSaves;
    }
}

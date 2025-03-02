package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
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

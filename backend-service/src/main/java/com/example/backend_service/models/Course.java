package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


import java.io.Serializable;
import java.util.*;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
//    @Override
//    public String toString() {
//        return "Course{" +
//                "courseID=" + courseID +
//                ", className='" + className + '\'' +
//                ", courseName='" + courseName + '\'' +
//                ", semester=" + semester +
//                ", startYear=" + startYear +
//                ", endYear=" + endYear +
//                '}';
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="course_id")
    private Long courseID;

    @Column(nullable = false, columnDefinition = "nvarchar(11)")
    private String className;

    @Column(nullable = false, columnDefinition = "nvarchar(40)")
    private String courseName;

    @Column(nullable = false)
    @Min(value = 1, message = "Semester must be 1 or 2.")
    @Max(value = 2, message = "Semester must be 1 or 2.")
    private int semester;

    @Column(nullable = false)
    @NotNull(message = "Start year cannot be null.")
    @Min(value = 2001, message = "Start year must be greater than 2000.")
    private int startYear;

    @Column(nullable = false)
    @NotNull(message = "End year cannot be null.")
    private int endYear;

    public void setEndYear(int endYear) {
        if (endYear != this.startYear + 1) {
            throw new IllegalArgumentException("End year must be the next year after start year.");
        }
        this.endYear = endYear;
    }

//    public Course(String className, String courseName, int semester, int startYear, int endYear) {
//        this.className = className;
//        this.courseName = courseName;
//        this.semester = semester;
//        this.startYear = startYear;
//        setEndYear(endYear);
//    }

    //Mapping
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses",
                    cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH}
    )
    @JsonIgnore
    private List<Lecturer> lectures = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Exam> exams = new ArrayList<>();

    public List<Exam> getExams() {
        return exams;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }
}

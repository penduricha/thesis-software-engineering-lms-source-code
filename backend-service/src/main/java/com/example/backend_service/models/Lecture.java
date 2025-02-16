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
public class Lecture implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, columnDefinition = "nvarchar(8)", name ="lecture_id")
    private String lectureID;

    @Column(nullable = false, columnDefinition = "nvarchar(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "nvarchar(30)", unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "nvarchar(30)", unique = true)
    private String phoneNumber;

    //Hashed by sha512()
    @Column(nullable = false, columnDefinition = "nvarchar(130)")
    private String password;

    //map
    //tạo 1 bảng map quan hệ nhiều nhiều.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "map_lecture_course",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public String getLectureID() {
        return lectureID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}

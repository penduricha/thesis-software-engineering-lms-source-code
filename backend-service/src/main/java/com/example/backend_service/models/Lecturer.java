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
public class Lecturer implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "nvarchar(8)", name ="lecturer_id")
    private String lecturerID;

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
            name = "map_lecturer_course",
            joinColumns = @JoinColumn(name = "lecturer_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();



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

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

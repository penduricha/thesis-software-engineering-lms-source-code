package com.example.backend_service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
public class Lecture implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, columnDefinition = "nvarchar(8)")
    private String lectureID;


}

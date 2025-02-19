package com.example.backend_service.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Data
public class StudentCredentialsDTO {
    // Getters
    private String studentID;
    private String password;

    public StudentCredentialsDTO(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }

}

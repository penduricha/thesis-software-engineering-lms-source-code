package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Entity
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class BankTestCaseJavaCore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_test_case_id", nullable = false)
    private Long bankTestCaseID;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String inputTest;

    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String outputExpect;

    @Column(columnDefinition = "nvarchar(50)")
    private String note;

    //map with Bank question java core
    @ManyToOne
    @JoinColumn(name = "question_java_core_id")
    @JsonIgnore
    private BankQuestionJavaCore bankQuestionJavaCore;

    @Override
    public String toString() {
        return "BankTestCaseJavaCore{" +
                "note='" + note + '\'' +
                ", outputExpect='" + outputExpect + '\'' +
                ", inputTest='" + inputTest + '\'' +
                ", bankTestCaseID=" + bankTestCaseID +
                '}';
    }

    public Long getBankTestCaseID() {
        return bankTestCaseID;
    }

    public String getInputTest() {
        return inputTest;
    }

    public String getOutputExpect() {
        return outputExpect;
    }

    public String getNote() {
        return note;
    }
}

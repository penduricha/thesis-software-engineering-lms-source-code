package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OutputDebugResultJavaCore implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "output_debug_java_core_id", nullable = false)
    private Long outputDebugJavaCoreID;

    @Column(columnDefinition = "longtext")
    private String outputCodeStudent;

    //field này sẽ cập nhật so sánh bên sql đưa trên fail hay pass.
    private boolean fail;

    @PrePersist
    protected void onCreate() {
        fail = true;
    }

    @ManyToOne
    @JoinColumn(name = "result_question_java_core_id")
    @JsonIgnore
    private ResultQuestionJavaCore resultQuestionJavaCore;

    public Long getOutputDebugJavaCoreID() {
        return outputDebugJavaCoreID;
    }

    public void setOutputDebugJavaCoreID(Long outputDebugJavaCoreID) {
        this.outputDebugJavaCoreID = outputDebugJavaCoreID;
    }

    public String getOutputCodeStudent() {
        return outputCodeStudent;
    }

    public void setOutputCodeStudent(String outputCodeStudent) {
        this.outputCodeStudent = outputCodeStudent;
    }

    public boolean isFail() {
        return fail;
    }

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public ResultQuestionJavaCore getResultQuestionJavaCore() {
        return resultQuestionJavaCore;
    }

    public void setResultQuestionJavaCore(ResultQuestionJavaCore resultQuestionJavaCore) {
        this.resultQuestionJavaCore = resultQuestionJavaCore;
    }
}

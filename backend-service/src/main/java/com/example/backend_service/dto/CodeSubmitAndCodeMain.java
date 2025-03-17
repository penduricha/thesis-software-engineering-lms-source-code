package com.example.backend_service.dto;

import java.io.Serializable;

public class CodeSubmitAndCodeMain implements Serializable {
    private Long resultQuestionJavaCoreID;

    private String codeStudentSubmitted;

    public CodeSubmitAndCodeMain(Long resultQuestionJavaCoreID, String codeStudentSubmitted, String codeRunToOutput) {
        this.resultQuestionJavaCoreID = resultQuestionJavaCoreID;
        this.codeStudentSubmitted = codeStudentSubmitted;
        this.codeRunToOutput = codeRunToOutput;
    }

    public Long getResultQuestionJavaCoreID() {
        return resultQuestionJavaCoreID;
    }

    public void setResultQuestionJavaCoreID(Long resultQuestionJavaCoreID) {
        this.resultQuestionJavaCoreID = resultQuestionJavaCoreID;
    }

    private String codeRunToOutput;

    public String getCodeStudentSubmitted() {
        return codeStudentSubmitted;
    }

    public void setCodeStudentSubmitted(String codeStudentSubmitted) {
        this.codeStudentSubmitted = codeStudentSubmitted;
    }

    public String getCodeRunToOutput() {
        return codeRunToOutput;
    }

    public void setCodeRunToOutput(String codeRunToOutput) {
        this.codeRunToOutput = codeRunToOutput;
    }



    public CodeSubmitAndCodeMain() {
    }

    public String mergeToCode() {
        //StringBuilder sb = new StringBuilder();
        return codeStudentSubmitted + "\n" + codeRunToOutput;
    }
}

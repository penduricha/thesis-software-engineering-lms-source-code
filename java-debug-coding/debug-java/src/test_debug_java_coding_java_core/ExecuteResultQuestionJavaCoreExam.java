package test_debug_java_coding_java_core;

import java.io.Serializable;

public class ExecuteResultQuestionJavaCoreExam implements Serializable {
    private String studentID;

    private Long resultQuestionJavaCoreID;

    private String codeStudentSubmitted;

    private String codeRunToOutput;


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Long getResultQuestionJavaCoreID() {
        return resultQuestionJavaCoreID;
    }

    public void setResultQuestionJavaCoreID(Long resultQuestionJavaCoreID) {
        this.resultQuestionJavaCoreID = resultQuestionJavaCoreID;
    }

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

    public ExecuteResultQuestionJavaCoreExam() {
    }

    public ExecuteResultQuestionJavaCoreExam(String studentID, Long resultQuestionJavaCoreID, String codeStudentSubmitted, String codeRunToOutput) {
        this.studentID = studentID;
        this.resultQuestionJavaCoreID = resultQuestionJavaCoreID;
        this.codeStudentSubmitted = codeStudentSubmitted;
        this.codeRunToOutput = codeRunToOutput;
    }
}

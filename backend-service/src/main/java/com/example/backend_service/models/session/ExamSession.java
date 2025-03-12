package com.example.backend_service.models.session;

import java.io.Serializable;

public class ExamSession implements Serializable {
    private String studentID;
    private long examID;
    private long time; // Thời gian còn lại tính bằng giây

    // Constructor, getters, and setters


    public ExamSession() {
    }

    public ExamSession(String studentID, long examID, long time) {
        this.studentID = studentID;
        this.examID = examID;
        this.time = time;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public long getExamID() {
        return examID;
    }

    public void setExamID(long examID) {
        this.examID = examID;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
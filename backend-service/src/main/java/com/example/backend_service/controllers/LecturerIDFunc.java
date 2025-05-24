package com.example.backend_service.controllers;

public class LecturerIDFunc {
    public static String covertLectureID(String lectureID) {
        if(lectureID.charAt(0) == '0'){
            return lectureID.substring(1);
        }
        return null;
    }
}

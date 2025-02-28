package com.example.backend_service.controllers;

import com.example.backend_service.models.session.ExamSession;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/session")
public class ExamSessionController {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @PostMapping("/create-session-time")
    public String createSession(@RequestBody ExamSession examSession, HttpSession httpSession) {
        // Chuyển đổi từ phút sang giây
        examSession.setTime(examSession.getTime() * 60);

        // Lấy danh sách session hiện tại
        List<ExamSession> sessions = (List<ExamSession>) httpSession.getAttribute("examSessions");
        if (sessions == null) {
            sessions = new ArrayList<>();
            httpSession.setAttribute("examSessions", sessions);
        }

        // Kiểm tra xem session đã tồn tại chưa
        for (ExamSession session : sessions) {
            if (session.getStudentID().equals(examSession.getStudentID())) {
                return "Session already exists for studentID: " + examSession.getStudentID();
            }
        }

        // Thêm session mới vào danh sách
        sessions.add(examSession);

        // Bắt đầu luồng đếm ngược
        scheduler.scheduleAtFixedRate(() -> {
            List<ExamSession> currentSessions = (List<ExamSession>) httpSession.getAttribute("examSessions");
            if (currentSessions != null) {
                // Duyệt qua danh sách để xóa session đã hết thời gian
                currentSessions.removeIf(session -> {
                    long remainingTime = session.getTime();
                    if (remainingTime <= 0) {
                        //System.out.println("Session expired and removed for studentID: " + session.getStudentID());
                        return true; // Trả về true để xóa session này
                    } else {
                        // Giảm thời gian
                        session.setTime(remainingTime - 1);
                        String formattedTime = formatTime(remainingTime);
                        //System.out.println("Time remaining for student " + session.getStudentID() + ": " + formattedTime);
                        return false; // Không xóa session
                    }
                });

                // Cập nhật lại danh sách session trong httpSession
                httpSession.setAttribute("examSessions", currentSessions);
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Trả về thông tin về session mới
        return "Session created for studentID: " + examSession.getStudentID() + " with countdown: " + formatTime(examSession.getTime());
    }

    @GetMapping("/time/{studentID}")
    public String getSessionTime(@PathVariable String studentID, HttpSession httpSession) {
        List<ExamSession> currentSessions = (List<ExamSession>) httpSession.getAttribute("examSessions");
        if (currentSessions != null) {
            for (ExamSession session : currentSessions) {
                if (session.getStudentID().equals(studentID)) {
                    long remainingTime = session.getTime();
                    return formatTime(remainingTime);
                }
            }
        }
        return null;
    }

    @DeleteMapping("/delete-all-session-time")
    public String deleteAllSessions(HttpSession httpSession) {
        httpSession.removeAttribute("examSessions");
        return "All sessions have been deleted.";
    }

    private String formatTime(long seconds) {
        long minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

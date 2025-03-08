package com.example.backend_service.services;


import org.springframework.stereotype.Service;

@Service
public class ExamSessionService {
//    @Autowired
//    private SessionRepository<? extends Session> sessionRepository;
//
//    public void startSessionCountdown(String sessionId, long durationMinutes) {
//        AtomicLong remainingTime = new AtomicLong(durationMinutes * 60); // Chuyển sang giây
//
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(() -> {
//            remainingTime.getAndDecrement();
//
//            if (remainingTime.get() <= 0) {
//                sessionRepository.deleteById(sessionId);
//                System.out.println("Session " + sessionId + " has expired and been deleted.");
//                scheduler.shutdown();
//            } else {
//                // Cập nhật thời gian còn lại vào session
//                Session session = sessionRepository.findById(sessionId);
//                if (session != null) {
//                    ExamSession examSession = session.getAttribute("examSession");
//                    if (examSession != null) {
//                        examSession.setRemainingTime(remainingTime.get());
//                    }
//                }
//            }
//        }, 0, 1, TimeUnit.SECONDS);
//    }
}

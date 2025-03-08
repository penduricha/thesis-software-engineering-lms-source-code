package com.example.backend_service.controllers;


import com.example.backend_service.services.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // Cho phép CORS từ mọi origin (CHỈ dùng cho development)
public class ChatController {

    @Autowired
    private final GeminiService geminiService;

    public ChatController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generateContent(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        String response = geminiService.generateContent(prompt);
        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        return ResponseEntity.ok(result);
    }
}
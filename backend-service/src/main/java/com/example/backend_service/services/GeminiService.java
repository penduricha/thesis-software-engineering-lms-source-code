package com.example.backend_service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import java.util.Map;
import java.util.List;
import java.util.HashMap;


@Service
public class GeminiService {

    //@Value("${gemini.api.key}")
    private String apiKey = "AIzaSyAb6mloHBgiaYo9TFjcYHmTSpVPlxfPy34";

    private final String MODEL_NAME = "gemini-2.0-flash"; // hoặc phiên bản khác nếu có
    private final String API_VERSION = "v1beta"; // Explicitly define API version

    public String generateContent(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://generativelanguage.googleapis.com/%s/models/%s:generateContent?key=%s",
                API_VERSION, MODEL_NAME, apiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> part = new HashMap<>();
        part.put("text", prompt);

        List<Map<String, Object>> parts = List.of(part); // Simplify list creation

        Map<String, Object> content = new HashMap<>();
        content.put("parts", parts);

        List<Map<String, Object>> contents = List.of(content);  // Simplify list creation

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("contents", contents);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            @SuppressWarnings("unchecked") // Suppress warning about unchecked cast
            Map<String, Object> response = restTemplate.postForObject(url, requestEntity, Map.class);


            if (response != null && response.containsKey("candidates")) {
                List<?> candidates = (List<?>) response.get("candidates");  // Correctly handle generic list

                if (candidates != null && !candidates.isEmpty()) {
                    Map<?, ?> candidate = (Map<?, ?>) candidates.get(0);   // Correctly handle generic map

                    if (candidate.containsKey("content")) {
                        Map<?, ?> contentResponse = (Map<?, ?>) candidate.get("content");

                        if (contentResponse.containsKey("parts")) {
                            List<?> partsResponse = (List<?>) contentResponse.get("parts");

                            if (partsResponse != null && !partsResponse.isEmpty()) {
                                Map<?, ?> partResponse = (Map<?, ?>) partsResponse.get(0);

                                if (partResponse.containsKey("text")) {
                                    return (String) partResponse.get("text");
                                }
                            }
                        }
                    }
                }
            }

            return "Không có phản hồi từ Gemini.";

        } catch (Exception e) {
            System.err.println("Lỗi khi gọi Gemini API: " + e.getMessage());
            return "Lỗi: " + e.getMessage();
        }
    }
}
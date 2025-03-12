package com.example.backend_service.services;



import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {
    private final OpenAiChatModel chatModel;

    public ChatGPTService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String promptString) {
        Prompt prompt = new Prompt(promptString);
        ChatResponse response = chatModel.call(prompt); // Gọi OpenAI API và nhận phản hồi
        return response.getResult().getOutput().getText(); // Lấy nội dung phản hồi từ API
    }

}


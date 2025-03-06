package com.example.backend_service.dto;



public class ChatMessage {
    private String sender;
    private String text;

    public ChatMessage() {} // Cần có constructor mặc định cho Jackson

    public ChatMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

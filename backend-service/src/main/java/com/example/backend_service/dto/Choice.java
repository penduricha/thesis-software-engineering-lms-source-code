package com.example.backend_service.dto;

import lombok.Data;

@Data
public class Choice {
    private int index;
    private Message message;

    public Choice(int index, Message message) {
        this.index = index;
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

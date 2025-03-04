package com.example.backend_service.dto;



import lombok.Data;
import java.util.List;

@Data
public class ChatResponse {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public ChatResponse(List<Choice> choices) {
        this.choices = choices;
    }
}






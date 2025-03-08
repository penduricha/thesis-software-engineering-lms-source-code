package com.example.backend_service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class BackendServiceApplication {
	@Autowired
	OpenAiChatModel chatModel;

	public static void main(String[] args) {
		SpringApplication.run(BackendServiceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Prompt prompt = new Prompt ("Hello chatgpt");
//		ChatResponse response = chatModel.call(prompt);
//		System.out.println(response.getResult().getOutput().getText());
//	}
}

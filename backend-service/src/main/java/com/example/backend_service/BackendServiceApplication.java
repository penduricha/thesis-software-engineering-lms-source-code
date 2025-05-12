package com.example.backend_service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
//@Transactional
public class BackendServiceApplication {
//	@PersistenceContext
//	private static EntityManager entityManager;

    public static void main(String[] args) {
		SpringApplication.run(BackendServiceApplication.class, args);
		/*
			 Ở đây em set up khi record chua co data se tu nap dataset vao khi service duoc run
			Tranh day bat tien khi phai insert chay
			O day chị chỉ cần insert de bai vao thoi
			Step 6: import bank_question_java_core.sql
			Step 7: import bank_test_case_java_core.sql
			Step 8: import file bank_test_java_oop.sql
		 */
	}
}

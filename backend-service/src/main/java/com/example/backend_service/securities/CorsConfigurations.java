package com.example.backend_service.securities;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfigurations implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "http://localhost:5173", //vue
                        "http://localhost:5174",
                        "http://localhost:3000",  // ReactJS
                        "http://localhost:8081",
                        // React Native may minh
                        // dùng http://localhost:8082
                        "http://localhost:4200",  // Angular
                        "http://localhost:3001" ,//Next.js
                        "http://127.0.0.1:5173/",//
                        "http://127.0.0.1:5174/",
                        "https://thesis-software-engineering-lms-source-code-6a7u.vercel.app/",
                        "https://thesis-software-engineering-lms-source-code-q7q8.vercel.app",
                        "https://thesis-software-engineering-lms-git-04148c-pendurichas-projects.vercel.app/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
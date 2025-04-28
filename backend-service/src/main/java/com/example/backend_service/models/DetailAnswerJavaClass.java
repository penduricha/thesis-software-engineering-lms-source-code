package com.example.backend_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailAnswerJavaClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long detailAnswerJavaClassID;

    @Column(nullable = false, columnDefinition = "longtext")
    private String sentence;

    @Min(value = 0, message = "Score achievement must be greater than 0.")
    @Max(value = 10, message = "Score achievement must be lower than 10.")
    @Column(nullable = false)
    private double scoreAchievement;

    @Min(value = 0, message = "Max score must be greater than 0.")
    @Max(value = 10, message = "Max score must be lower than 10.")
    @Column(nullable = false)
    private double maxScore;

    @Column(nullable = false, columnDefinition = "longtext")
    private String reviews;

    //map with detail mark student
    @ManyToOne
    @JoinColumn(name = "detail_mark_student_id")
    @JsonIgnore
    private DetailMarkStudent detailMarkStudent;

    public Long getDetailAnswerJavaClassID() {
        return detailAnswerJavaClassID;
    }

    public void setDetailAnswerJavaClassID(Long detailAnswerJavaClassID) {
        this.detailAnswerJavaClassID = detailAnswerJavaClassID;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public double getScoreAchievement() {
        return scoreAchievement;
    }

    public void setScoreAchievement(double scoreAchievement) {
        if(scoreAchievement > maxScore)
            this.scoreAchievement = maxScore;
        else
            this.scoreAchievement = scoreAchievement;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public DetailMarkStudent getDetailMarkStudent() {
        return detailMarkStudent;
    }

    public void setDetailMarkStudent(DetailMarkStudent detailMarkStudent) {
        this.detailMarkStudent = detailMarkStudent;
    }
}

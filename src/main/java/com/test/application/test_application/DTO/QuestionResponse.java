package com.test.application.test_application.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponse {
    private String questionId;
    private String question;
    private String type;
    private String imageURL;
    private float negativeMarks;
    private String level;
    private float marks;
    private LocalDateTime createdAt;
    private List<OptionDTO> options;

    public float getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(float negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }
}

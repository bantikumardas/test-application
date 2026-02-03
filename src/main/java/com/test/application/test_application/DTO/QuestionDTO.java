package com.test.application.test_application.DTO;

import java.util.List;

public class QuestionDTO {
    private String question;
    private String type;
    private String imageURL;
    private float marks;
    private float negativeMarks;
    private String level;
    private String testId;
    private List<OptionDTO> options;
    private int answerIndex;


    public List<OptionDTO> getOptions() {
        return options;
    }

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

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}

package com.test.application.test_application.DTO;



public class TestDTO {
    private String testId;
    private String testName;
    private String description;
    private int totalQuestions;
    private int durationMin;
    private String userId;
    private String catagoryId;

    public TestDTO(String description, String testName, int totalQuestions, int durationMin, String userId, String catagoryId) {
        this.description = description;
        this.testName = testName;
        this.totalQuestions = totalQuestions;
        this.durationMin = durationMin;
        this.userId = userId;
        this.catagoryId = catagoryId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(String catagoryId) {
        this.catagoryId = catagoryId;
    }
}

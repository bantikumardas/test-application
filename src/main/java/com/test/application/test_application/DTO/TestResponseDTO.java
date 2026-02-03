package com.test.application.test_application.DTO;



public class TestResponseDTO {
    private String responseId;
    private String answer;
    private String questionId;
    private String userId;
    private String choosenOptionId;
    private String testId;
    private String testSessionId;


    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getTestSessionId() {
        return testSessionId;
    }

    public void setTestSessionId(String testSessionId) {
        this.testSessionId = testSessionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChoosenOptionId() {
        return choosenOptionId;
    }

    public void setChoosenOptionId(String choosenOptionId) {
        this.choosenOptionId = choosenOptionId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}

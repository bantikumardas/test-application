package com.test.application.test_application.DTO;


public class OptionDTO {
    private String optionId;
    private String description;
    private String imageURL;

    public OptionDTO() {
    }

    public OptionDTO(String description, String imageURL) {
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

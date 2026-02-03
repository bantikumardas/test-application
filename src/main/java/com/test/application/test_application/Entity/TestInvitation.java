package com.test.application.test_application.Entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class TestInvitation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String invitationId;
    @ManyToOne
    @JoinColumn(name = "send_by")
    private Users sendBy;
    @NotNull
    private String sendToEmail;
    @NotNull
    private String uniqueCode;
    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;
    private String status;
    private Boolean isEmailSend;
    private String companyName;
    private LocalDateTime expiryTime;

    public Boolean getEmailSend() {
        return isEmailSend;
    }

    public void setEmailSend(Boolean emailSend) {
        isEmailSend = emailSend;
    }

    public Users getSendBy() {
        return sendBy;
    }

    public void setSendBy(Users sendBy) {
        this.sendBy = sendBy;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getSendToEmail() {
        return sendToEmail;
    }

    public void setSendToEmail(String sendToEmail) {
        this.sendToEmail = sendToEmail;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

//    public Test getTest() {
//        return test;
//    }
//
//    public void setTest(Test test) {
//        this.test = test;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}

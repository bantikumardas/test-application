package com.test.application.test_application.Entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class TestResponse {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String testResponseId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    private int totalQuestions;
    private int attempt_question;
    private int correct_questions;
    private float obtained_marks;
    private float positive_marks;
    private float negative_marks;
    private float total_marks;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
}

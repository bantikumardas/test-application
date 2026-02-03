package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepo extends JpaRepository<Answer, String> {
}

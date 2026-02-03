package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OptionRepo extends JpaRepository<Options, String> {

    @Query("SELECT o FROM Options o WHERE o.questions.questionId = :questionId")
    List<Options> findByQuestions_QuestionId(@Param("questionId") String questionId);
}

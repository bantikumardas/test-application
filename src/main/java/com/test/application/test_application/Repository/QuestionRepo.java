package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Questions,String> {

    @Query("SELECT q FROM Questions q WHERE q.test.testId = :testId")
    List<Questions> findByTestId(@Param("testId") String testId);
}

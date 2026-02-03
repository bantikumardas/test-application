package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.TestSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSessionRepo extends JpaRepository<TestSession, String> {

}

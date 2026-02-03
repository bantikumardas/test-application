package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepo extends JpaRepository<Response, String> {
}

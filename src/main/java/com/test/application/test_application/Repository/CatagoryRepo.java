package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatagoryRepo extends JpaRepository<Catagory, String> {
}

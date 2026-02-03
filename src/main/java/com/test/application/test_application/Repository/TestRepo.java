package com.test.application.test_application.Repository;

import com.test.application.test_application.Entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepo extends JpaRepository<Test, String> {

    Page<Test> findByCreatedBy_UserId(String userId, Pageable pageable);
    Page<Test> findByCreatedBy_UserIdAndCatagory_Name(String userId, String categoryName, Pageable pageable);

    Page<Test> findByCreatedBy_UserIdAndIsActive(String userId, boolean isActive, Pageable pageable);
    Page<Test> findByCreatedBy_UserIdAndCatagory_NameAndIsActive(String userId, String categoryName, boolean isActive, Pageable pageable);

    //Search only (No filters)
    Page<Test> findByCreatedBy_UserIdAndTestNameContainingIgnoreCase(String userId, String query, Pageable pageable);

    //Search AND Status
    Page<Test> findByCreatedBy_UserIdAndIsActiveAndTestNameContainingIgnoreCase(String userId, boolean isActive, String query, Pageable pageable);

    //Search AND Category
    Page<Test> findByCreatedBy_UserIdAndCatagory_NameAndTestNameContainingIgnoreCase(String userId, String categoryName, String query, Pageable pageable);

    //Search AND Category AND Status (All criteria)
    Page<Test> findByCreatedBy_UserIdAndCatagory_NameAndIsActiveAndTestNameContainingIgnoreCase(String userId, String categoryName, boolean isActive, String query, Pageable pageable);

}

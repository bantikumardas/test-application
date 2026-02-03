package com.test.application.test_application.Service;

import com.test.application.test_application.DTO.TestInvitationDTO;
import com.test.application.test_application.Entity.Test;
import com.test.application.test_application.Entity.TestInvitation;
import com.test.application.test_application.Entity.Users;
import com.test.application.test_application.Repository.TestRepo;
import com.test.application.test_application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TestActivityService {

//    @Autowired
//    private UserRepo userRepo;
//    @Autowired
//    private EmailService emailService;
//    @Autowired
//    private TestRepo testRepo;
//
//    public ResponseEntity<?> sendTestInvitation(TestInvitationDTO testInvitationDTO){
//        try {
//            TestInvitation testInvitation=new TestInvitation();
//            Random random = new Random();
//            String code = 100_000_000 + random.nextInt(900_000_000)+"";
//            Users user=findAuthUser();
//            String senderEmail=testInvitationDTO.getSendToEmail();
//            Optional<Test> test=testRepo.findById(testInvitationDTO.getTestId());
//            if(test.isEmpty()){
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test is not found");
//            }
//            emailService.sendInvitation(senderEmail, code,testInvitationDTO,user,test.get());
//            return ResponseEntity.status(HttpStatus.OK).body("test");
//
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.OK).body("error "+e.getMessage());
//        }
//    }
//
//
//
//    public Users findAuthUser(){
//        return new Users();
//    }
}

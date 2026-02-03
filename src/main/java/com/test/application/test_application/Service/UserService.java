package com.test.application.test_application.Service;


import com.test.application.test_application.DTO.UserDTO;
import com.test.application.test_application.Entity.Users;
import com.test.application.test_application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createUser(UserDTO userDTO) {
        try {
            Users users=new Users();
            users.setFullName(userDTO.getFullName());
            users.setEmailId(userDTO.getEmailId());
            users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            users.setPhoneNumber(userDTO.getPhoneNumber());
            users.setRole(userDTO.getRole());


            return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(users));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }
}

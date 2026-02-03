package com.test.application.test_application.Controller;

import com.test.application.test_application.Configuration.JwtUtil;
import com.test.application.test_application.DTO.AuthRequest;
import com.test.application.test_application.DTO.UserDTO;
import com.test.application.test_application.Entity.Users;
import com.test.application.test_application.Repository.UserRepo;
import com.test.application.test_application.Service.MyUserDetailsService;
import com.test.application.test_application.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepo userRepo;


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getEmail());
        String token=jwtUtil.generateToken(userDetails.getUsername());
        String role=userDetails.getAuthorities().stream().
                map(auth -> auth.getAuthority())
                .findFirst()
                .orElse("USER");
        Map<String, String > res=new HashMap<>();
        Optional<Users> user=userRepo.findByEmailId(userDetails.getUsername());
        //poke
        res.put("token", token);
        res.put("Role", role);
        res.put("name", user.get().getFullName());
        res.put("userId",user.get().getUserId());
        res.put("emailId", user.get().getEmailId());
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        return  userService.createUser(userDTO);
    }

}

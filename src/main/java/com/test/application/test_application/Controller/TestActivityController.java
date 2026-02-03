package com.test.application.test_application.Controller;

import com.test.application.test_application.DTO.TestInvitationDTO;
import com.test.application.test_application.Entity.Response;
import com.test.application.test_application.Service.TestActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test-activity")
public class TestActivityController {

//    @Autowired
//    private TestActivityService testActivityService;
//
//    @PostMapping("/send/invitation")
//    public ResponseEntity<?> sendTestInvitation(@RequestBody TestInvitationDTO testInvitationDTO){
//        return testActivityService.sendTestInvitation(testInvitationDTO);
//    }
}

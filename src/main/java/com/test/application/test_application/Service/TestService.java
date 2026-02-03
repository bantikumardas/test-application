package com.test.application.test_application.Service;


import com.test.application.test_application.DTO.QuestionResponse;
import com.test.application.test_application.DTO.TestDTO;
import com.test.application.test_application.DTO.TestResponseDTO;
import com.test.application.test_application.DTO.TestSessionDTO;
import com.test.application.test_application.Entity.*;
import com.test.application.test_application.Entity.Enum.ROLE;
import com.test.application.test_application.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepo testRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CatagoryRepo catagoryRepo;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private TestSessionRepo testSessionRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private OptionRepo optionRepo;
    @Autowired
    private ResponseRepo responseRepo;

    public ResponseEntity<?> createTest(TestDTO testDto){
        try{
            Test test=new Test();
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Users user=userRepo.findByEmailId(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

            Catagory catagory=catagoryRepo.findById(testDto.getCatagoryId())
                    .orElseThrow(()->new RuntimeException("Catagory not found"));
            if(testDto.getTestId()!=null || testDto.getTestId()!=""){
                test.setTestId(testDto.getTestId());
            }
            test.setTestName(testDto.getTestName());
            test.setDescription(testDto.getDescription());
            test.setTotalQuestions(testDto.getTotalQuestions());
            test.setDurationMin(testDto.getDurationMin());
            test.setCreatedBy(user);
            test.setCatagory(catagory);

            return ResponseEntity.status(HttpStatus.CREATED).body(testRepo.save(test));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> createCatagory(Catagory catagory) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Users user=userRepo.findByEmailId(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            if(!user.getRole().equals(ROLE.ADMIN)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorize");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(catagoryRepo.save(catagory));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CREATED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> createTestSession(TestSessionDTO testSessionDTO) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Optional<Users> user=userRepo.findByEmailId(email);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login first");
            }
            Optional<Test> test=testRepo.findById(testSessionDTO.getTestId());
            if(test.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct test Id");
            TestSession testSession=new TestSession();
            testSession.setTest(test.get());
            testSession.setUser(user.get());
            testSession.setStartTime(LocalDateTime.now());

            TestSession testSessionSaved=testSessionRepo.save(testSession);
            List<QuestionResponse> responses=questionService.getAllQuestion(testSessionDTO.getTestId());
            Map<String, Object> toSend=new HashMap<>();
            toSend.put("SessionID", testSessionSaved.getSession_id());
            toSend.put("Question", responses);
            return ResponseEntity.status(HttpStatus.CREATED).body(toSend);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> saveQuestions(List<TestResponseDTO> testResponseDTO) {
        try {

            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Optional<Users> user=userRepo.findByEmailId(email);
            Optional<Test> test=testRepo.findById(testResponseDTO.getFirst().getTestId());
            if(test.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Test not found");
            Optional<TestSession> testSession=testSessionRepo.findById(testResponseDTO.getFirst().getTestSessionId());
            if(testSession.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid test session");

            Duration duration = Duration.between(testSession.get().getStartTime(), LocalDateTime.now());
            // Get difference in minutes
            long seconds = duration.toSeconds();
            if(seconds>test.get().getDurationMin()*60){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Test time is over");
            }

            List<Response> responseList=testResponseDTO
                    .stream().map(dto->{
                        Response response = new Response();
                        response.setResponseId(dto.getResponseId());
                        response.setAnswer(dto.getAnswer());
                        response.setQuestion(questionRepo.findById(dto.getQuestionId()).get());
                        response.setTest(test.get());
                        response.setUser(user.get());
                        response.setChoosenOption(optionRepo.findById(dto.getChoosenOptionId()).get());
                        return response;
            }).collect(Collectors.toList());
            responseRepo.saveAll(responseList);

            List<TestResponseDTO> savedQuestionId=responseList.stream()
                    .map(res->{
                        TestResponseDTO resDto=new TestResponseDTO();
                        resDto.setResponseId(res.getResponseId());
                        resDto.setAnswer(res.getAnswer());
                        resDto.setQuestionId(res.getQuestion().getQuestionId());
                        resDto.setTestId(res.getTest().getTestId());
                        resDto.setChoosenOptionId(res.getChoosenOption().getOptionId());
                        resDto.setTestSessionId(testResponseDTO.getFirst().getTestSessionId());
                        return resDto;
                    }).toList();
            return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestionId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> getAllTestCreatedByLoginUser(String query,int page, int recordPerPage, String sortBy, String sortDirection, String statusFilter, String categoryFilter) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String email=authentication.getName();
            Optional<Users> user=userRepo.findByEmailId(email);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error :User not found");
            }
            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort=Sort.by(direction, sortBy);
            Pageable pageable = PageRequest.of(page, recordPerPage, sort);
            Page<Test> tests;
            String userId = user.get().getUserId();

            boolean isQueryPresent = query != null && !query.trim().isEmpty();
            boolean isCategoryFiltered = categoryFilter != null && !categoryFilter.trim().isEmpty() && !categoryFilter.equalsIgnoreCase("All");
            boolean isStatusFiltered = statusFilter != null && !statusFilter.trim().isEmpty() && !statusFilter.equalsIgnoreCase("All");

            boolean isActiveStatus = !isStatusFiltered || !statusFilter.equalsIgnoreCase("inactive");

            // --- Conditional Repository Calls ---

            if (isQueryPresent) {
                // CASE A: SEARCH QUERY IS PRESENT
                String searchQuery = query; // Use the query for filtering

                if (isCategoryFiltered && isStatusFiltered){
                    // A.1: Search + Category + Status
                    tests = testRepo.findByCreatedBy_UserIdAndCatagory_NameAndIsActiveAndTestNameContainingIgnoreCase(
                            userId, categoryFilter, isActiveStatus, searchQuery, pageable
                    );
                } else if(isCategoryFiltered) {
                    // A.2: Search + Category
                    tests = testRepo.findByCreatedBy_UserIdAndCatagory_NameAndTestNameContainingIgnoreCase(
                            userId, categoryFilter, searchQuery, pageable
                    );
                } else if(isStatusFiltered){
                    // A.3: Search + Status
                    tests = testRepo.findByCreatedBy_UserIdAndIsActiveAndTestNameContainingIgnoreCase(
                            userId, isActiveStatus, searchQuery, pageable
                    );
                } else {
                    // A.4: Search only
                    tests = testRepo.findByCreatedBy_UserIdAndTestNameContainingIgnoreCase(
                            userId, searchQuery, pageable
                    );
                }
            } else {
                //  CASE B: NO SEARCH QUERY (Use existing logic)
                if (isCategoryFiltered && isStatusFiltered){
                    // B.1: Category + Status
                    tests = testRepo.findByCreatedBy_UserIdAndCatagory_NameAndIsActive(
                            userId, categoryFilter, isActiveStatus, pageable
                    );
                } else if(isCategoryFiltered) {
                    // B.2: Category only
                    tests = testRepo.findByCreatedBy_UserIdAndCatagory_Name(userId, categoryFilter, pageable);
                } else if(isStatusFiltered){
                    // B.3: Status only
                    tests = testRepo.findByCreatedBy_UserIdAndIsActive(userId, isActiveStatus, pageable);
                } else {
                    // B.4: Base (No filters)
                    tests = testRepo.findByCreatedBy_UserId(userId, pageable);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(tests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> getAllCatagory() {
        try {
            List<Catagory> list=catagoryRepo.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> getTestById(String testId) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(testRepo.findById(testId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public Users findAuthUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email=authentication.getName();
        Optional<Users> user=userRepo.findByEmailId(email);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    public ResponseEntity<?> activateDeactivateTest(String testId) {
        try{
            Users users=findAuthUser();
            if(!users.getRole().equals(ROLE.ADMIN)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not authorize");
            }
            System.out.println("The test Id is : "+testId);
            Optional<Test> test= testRepo.findById(testId);
            if(test.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provided test id is incorrect");
            }
            Test test1=test.get();
            test1.setActive(!test1.isActive());
            testRepo.save(test1);
            return ResponseEntity.status(HttpStatus.OK).body("Success");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }
}

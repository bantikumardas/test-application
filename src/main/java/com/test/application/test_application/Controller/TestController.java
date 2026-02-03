package com.test.application.test_application.Controller;

import com.test.application.test_application.DTO.TestDTO;
import com.test.application.test_application.DTO.TestResponseDTO;
import com.test.application.test_application.DTO.TestSessionDTO;
import com.test.application.test_application.Entity.Catagory;
import com.test.application.test_application.Service.QuestionService;
import com.test.application.test_application.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("create")
    public ResponseEntity<?> createTest(@RequestBody TestDTO test){
        return testService.createTest(test);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTestById(@PathVariable(name = "id") String testId){
        return testService.getTestById(testId);
    }

    @GetMapping
    public ResponseEntity<?> getAllTestCreatedByLoginUser(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "record-per-page", defaultValue = "10") int recordPerPage,
                                                          @RequestParam(name="sort-by", defaultValue = "updatedAt") String sortBy,
                                                          @RequestParam(name="sort-direction", defaultValue = "desc") String sortDirection,
                                                          @RequestParam(name="status-filter", required = false) String statusFilter,
                                                          @RequestParam(name ="category-filter", required = false) String categoryFilter,
                                                          @RequestParam(name="query", required = false) String query){
        return testService.getAllTestCreatedByLoginUser(query, page, recordPerPage, sortBy,sortDirection, statusFilter, categoryFilter);
    }

    @PostMapping("create/catagory")
    public ResponseEntity<?> createCatagory(@RequestBody Catagory catagory){
        return testService.createCatagory(catagory);
    }

    @GetMapping("get-all-catagory")
    public ResponseEntity<?> getAllCatagory(){
        return testService.getAllCatagory();
    }


    @GetMapping("all-question/{testId}")
    public ResponseEntity<?> getAllQuestionForThisTest(@PathVariable(name = "testId") String testId){
        return questionService.getAllQuestionFromTest(testId);
    }

    @PostMapping("create-session")
    public ResponseEntity<?> createTestSession(@RequestBody TestSessionDTO testSessionDTO){
        return testService.createTestSession(testSessionDTO);
    }

    @PostMapping("save-questions")
    public ResponseEntity<?> saveQuestion(@RequestBody List<TestResponseDTO> testResponseDTO){
        testResponseDTO.stream().forEach(n->{
            System.out.println(n.getQuestionId());
            System.out.println(n.getTestId());
            System.out.println(n.getAnswer());
            System.out.println(n.getTestSessionId());
            System.out.println(n.getChoosenOptionId());
        });
        return testService.saveQuestions(testResponseDTO);
    }

    @PutMapping("/activate-deactivate")
    public ResponseEntity<?> activateDeactivateTest(@RequestBody Map<String, String> request){
        String testId = request.get("testId");
        return testService.activateDeactivateTest(testId);
    }
}

package com.test.application.test_application.Service;

import com.test.application.test_application.DTO.OptionDTO;
import com.test.application.test_application.DTO.QuestionDTO;
import com.test.application.test_application.DTO.QuestionResponse;
import com.test.application.test_application.Entity.Answer;
import com.test.application.test_application.Entity.Options;
import com.test.application.test_application.Entity.Questions;
import com.test.application.test_application.Entity.Test;
import com.test.application.test_application.Exception.ResourceNotFoundException;
import com.test.application.test_application.Repository.AnswerRepo;
import com.test.application.test_application.Repository.OptionRepo;
import com.test.application.test_application.Repository.QuestionRepo;
import com.test.application.test_application.Repository.TestRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private TestRepo testRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private OptionRepo optionRepo;

    @Autowired
    private AnswerRepo answerRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<?> addQuestion(QuestionDTO questionDTO) {
        try {
            Test test = testRepo.findById(questionDTO.getTestId()).orElseThrow(
                    () -> new ResourceNotFoundException("Test is not found for this id " + questionDTO.getTestId())
            );
            Questions questions = new Questions();
            questions.setQuestion(questionDTO.getQuestion());
            questions.setMarks(questionDTO.getMarks());
            questions.setType(questionDTO.getType());
            questions.setImageURL(questionDTO.getImageURL());
            questions.setTest(test);
            questions.setLevel(questionDTO.getLevel());
            questions.setNegativeMarks(questionDTO.getNegativeMarks());


            if (questionDTO.getAnswerIndex() >= questionDTO.getOptions().size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select the answer");
            }
            Questions savedQuestion = questionRepo.save(questions);
            List<OptionDTO> options = questionDTO.getOptions();

            for (int i = 0; i < options.size(); i++) {
                OptionDTO opt = options.get(i);
                Options option = new Options(savedQuestion, opt.getDescription(), opt.getImageURL());
                Options ops = optionRepo.save(option);
                if (i == questionDTO.getAnswerIndex()) {
                    Answer answer = new Answer(savedQuestion, ops);
                    answerRepo.save(answer);
                }
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("SUCCESS");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());
        }
    }

    public ResponseEntity<?> getAllQuestionFromTest(String testId){
        try{
            List<QuestionResponse> responses=getAllQuestion(testId);
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error "+e.getMessage());

        }
    }

    public List<QuestionResponse> getAllQuestion(String testId){
        List<Questions> questionList=questionRepo.findByTestId(testId);
        List<QuestionResponse> responses=new ArrayList<>();
        for(Questions questions: questionList){
            List<Options> option=optionRepo.findByQuestions_QuestionId(questions.getQuestionId());
            QuestionResponse questionResponse=new QuestionResponse();
            questionResponse.setQuestionId(questions.getQuestionId());
            questionResponse.setQuestion(questions.getQuestion());
            questionResponse.setType(questions.getType());
            questionResponse.setImageURL(questions.getImageURL());
            questionResponse.setMarks(questions.getMarks());
            questionResponse.setCreatedAt(questions.getCreatedAt());
            questionResponse.setLevel(questions.getLevel());
            questionResponse.setNegativeMarks(questions.getNegativeMarks());


            List<OptionDTO> optionDTOS = option.stream()
                    .map(options -> modelMapper.map(options, OptionDTO.class))
                    .collect(Collectors.toList());

            questionResponse.setOptions(optionDTOS);

            responses.add(questionResponse);

        }
        return responses;
    }
}

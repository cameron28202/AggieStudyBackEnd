package com.aggieStudy.AggieStudy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aggieStudy.AggieStudy.model.Question;
import com.aggieStudy.AggieStudy.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PatchMapping("/{id}/{imageUrl}")
    public ResponseEntity<Question> updateQuestionImageUrl(@PathVariable UUID id, @PathVariable String imageUrl) {
        try{
            Question updatedQuestion = questionService.updateQuestionImageUrl(id, imageUrl);
            return ResponseEntity.ok(updatedQuestion);
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestionById(@PathVariable UUID questionId){
        questionService.deleteQuestion(questionId);
    }

    @PostMapping("/{examId}")
    public ResponseEntity<Question> addQuestionToExam(@PathVariable String examId, @RequestBody Question question) {
        Question savedQuestion = questionService.addQuestionToExam(examId, question);
        if (savedQuestion != null) {
            return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable UUID id) {
        return questionService.getQuestionById(id);
    }
}

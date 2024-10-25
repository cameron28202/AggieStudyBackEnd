package com.aggieStudy.AggieStudy;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aggieStudy.AggieStudy.model.Answer;
import com.aggieStudy.AggieStudy.service.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping
    public List<Answer> getAnswersByQuestionId(@PathVariable UUID questionId){
        return answerService.getAnswersByQuestionId(questionId);
    }

    @PostMapping("/{examId}")
    public ResponseEntity<Answer> addQuestionToExam(@PathVariable UUID questionId, @RequestBody Answer answer) {
        Answer savedAnswer = answerService.addAnswerToQuestion(answer, questionId);
        if (savedAnswer != null) {
            return new ResponseEntity<>(savedAnswer, HttpStatus.CREATED);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

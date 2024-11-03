package com.aggieStudy.AggieStudy.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggieStudy.AggieStudy.model.Answer;
import com.aggieStudy.AggieStudy.model.Question;
import com.aggieStudy.AggieStudy.repo.AnswerRepo;
import com.aggieStudy.AggieStudy.repo.QuestionRepo;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public List<Answer> getAllAnswers(){
        return answerRepo.findAll();
    }

    public List<Answer> getAnswersByQuestionId(UUID questionId){
        return answerRepo.findByQuestionId(questionId);
    }

    @Transactional
    public void deleteAnswer(UUID answerId) {
        Answer answer = answerRepo.findById(answerId)
            .orElseThrow(() -> new RuntimeException("Answer not found with id: " + answerId));
        
        Question question = answer.getQuestion();
        if (question != null) {
            question.getAnswers().remove(answer);
            questionRepo.save(question);
        }
        
        answerRepo.deleteById(answerId);
    }

    @Transactional
    public Answer addAnswerToQuestion(Answer answer, UUID questionId) {
        Question question = questionRepo.findById(questionId).orElse(null);
        if(question == null){
            return null;
        }
        answer.setQuestion(question);
        return answerRepo.save(answer);
    }

}

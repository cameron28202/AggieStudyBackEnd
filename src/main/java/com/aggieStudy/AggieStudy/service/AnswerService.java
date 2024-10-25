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
    private QuestionRepo questionRepo;

    public List<Answer> getAllAnswers(){
        return answerRepo.findAll();
    }

    public List<Answer> getAnswersByQuestionId(UUID questionId){
        return answerRepo.findByQuestionId(questionId);
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

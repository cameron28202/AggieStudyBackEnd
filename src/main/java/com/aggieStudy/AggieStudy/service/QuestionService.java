package com.aggieStudy.AggieStudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aggieStudy.AggieStudy.model.Answer;
import com.aggieStudy.AggieStudy.model.Exam;
import com.aggieStudy.AggieStudy.model.Question;
import com.aggieStudy.AggieStudy.repo.ExamRepo;
import com.aggieStudy.AggieStudy.repo.QuestionRepo;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepository;

    @Autowired
    private ExamRepo examRepository;

    @Transactional
    public Question updateQuestionImageUrl(UUID questionId, String imageUrl) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            question.setImageUrl(imageUrl);
            return questionRepository.save(question);
        } 
        else{
            throw new RuntimeException("Question not found with id: " + questionId);
        }
    }
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);
    }
    public List<Answer> getAnswersByQuestion(UUID questionId){
        Question question = questionRepository.findById(questionId).orElse(null);
        return question.getAnswers();
    }
    public Question getQuestionById(UUID id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Transactional
    public Question addQuestionToExam(String examId, Question question) {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam == null) {
            return null;
        }
        question.setExam(exam);
        return questionRepository.save(question);
    }
}

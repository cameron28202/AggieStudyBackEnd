package com.aggieStudy.AggieStudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aggieStudy.AggieStudy.model.Exam;
import com.aggieStudy.AggieStudy.model.Question;
import com.aggieStudy.AggieStudy.repo.ExamRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    @Autowired
    private ExamRepo examRepository;

    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }
    public Exam getExamById(String id){
        return examRepository.findById(id).orElse(null);
    }
    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }
    public void deleteExam(String id) {
        examRepository.deleteById(id);
    }
    public List<Exam> getExamsByCourseId(String courseId){
        return examRepository.findByCourseId(courseId);
    }
    public List<Question> getQuestionsByExamId(String examId){
        Optional<Exam> examOptional = examRepository.findById(examId);
        if (examOptional.isEmpty()) {
            throw new RuntimeException("Exam not found with id " + examId);
        }
        return examOptional.get().getQuestions();
    }
}

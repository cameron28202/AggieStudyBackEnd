package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Answer;
import com.aggieStudy.AggieStudy.model.Question;

import java.util.List;
import java.util.UUID;


@RepositoryRestResource
public interface QuestionRepo extends JpaRepository<Question, UUID> {
    List<Answer> findByQuestionId(UUID questionId);
}

package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Question;

import java.util.UUID;


@RepositoryRestResource
public interface QuestionRepo extends JpaRepository<Question, UUID> {
}

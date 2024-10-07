package com.aggieStudy.AggieStudy.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Exam;

import java.util.List;

@RepositoryRestResource
public interface ExamRepo extends JpaRepository<Exam, String> {
    List<Exam> findByCourseId(String courseId);
}

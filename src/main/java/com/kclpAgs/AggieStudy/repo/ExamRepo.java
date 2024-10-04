package com.kclpAgs.AggieStudy.repo;


import com.kclpAgs.AggieStudy.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ExamRepo extends JpaRepository<Exam, String> {
    List<Exam> findByCourseId(String courseId);
}

package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Course;

import java.util.List;

@RepositoryRestResource
public interface CourseRepo extends JpaRepository<Course, String> {

    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.id) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Course> findByNameOrIdContainingIgnoreCase(String query);
    List<Course> findBySubject(String subject);

}

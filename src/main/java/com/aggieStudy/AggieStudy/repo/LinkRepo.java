package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Link;

import java.util.List;

@RepositoryRestResource
public interface LinkRepo extends JpaRepository<Link, String> {
    List<Link> findByCourseId(String courseId);
}

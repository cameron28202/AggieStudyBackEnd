package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Resource;

import java.util.List;

@RepositoryRestResource
public interface ResourceRepo extends JpaRepository<Resource, String> {
    List<Resource> findByCourseId(String courseId);
}

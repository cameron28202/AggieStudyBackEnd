package com.aggieStudy.AggieStudy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aggieStudy.AggieStudy.model.Resource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface ResourceRepo extends JpaRepository<Resource, UUID> {
    List<Resource> findByCourseId(String courseId);
}

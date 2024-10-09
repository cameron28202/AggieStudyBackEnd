package com.aggieStudy.AggieStudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aggieStudy.AggieStudy.model.Resource;
import com.aggieStudy.AggieStudy.repo.ResourceRepo;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceService {
    @Autowired
    private ResourceRepo ResourceRepository;

    public List<Resource> getAllResources(){
        return ResourceRepository.findAll();
    }
    public Resource getResourceById(UUID id){
        return ResourceRepository.findById(id).orElse(null);
    }
    public Resource saveResource(Resource Resource) {
        return ResourceRepository.save(Resource);
    }
    public void deleteResource(UUID id) {
        ResourceRepository.deleteById(id);
    }
    public List<Resource> getResourcesByCourseId(String courseId){
        return ResourceRepository.findByCourseId(courseId);
    }

}

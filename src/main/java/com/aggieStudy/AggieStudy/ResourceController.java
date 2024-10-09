package com.aggieStudy.AggieStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aggieStudy.AggieStudy.model.Course;
import com.aggieStudy.AggieStudy.model.Resource;
import com.aggieStudy.AggieStudy.service.CourseService;
import com.aggieStudy.AggieStudy.service.ResourceService;


import java.util.UUID;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resources")
public class ResourceController {
    @Autowired
    private ResourceService ResourceService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Resource> getAllResources() {
        return ResourceService.getAllResources();
    }

    @GetMapping("/course/{courseId}")
    public List<Resource> getResourcesByCourseId(@PathVariable String courseId){
        return ResourceService.getResourcesByCourseId(courseId);
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable UUID id) {
        return ResourceService.getResourceById(id);
    }

    @PostMapping
    public Resource addResource(@RequestBody Resource Resource) {

        Optional<Course> courseOptional = courseService.getCourseById(Resource.getCourseId());
        if (courseOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid course ID");
        }

        return ResourceService.saveResource(Resource);
    }
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable UUID id) {
        ResourceService.deleteResource(id);
    }
}

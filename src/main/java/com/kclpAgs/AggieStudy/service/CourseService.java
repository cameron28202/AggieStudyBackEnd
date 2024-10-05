package com.kclpAgs.AggieStudy.service;

import com.kclpAgs.AggieStudy.model.Course;
import com.kclpAgs.AggieStudy.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesBySubject(String subject){
        return courseRepository.findBySubject(subject);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(String id, Course course) {
        Course existingCourse = courseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
        
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setSubject(course.getSubject());
        
        return courseRepository.save(existingCourse);
    }

    public List<Course> searchCourses(String query) {
        return courseRepository.findByNameOrIdContainingIgnoreCase(query);
    }
}

package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Course;
import com.geekbrains.springboot.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseService() {
    }

    public Course getCourseById(Long id) {
        return courseRepository.findOneById(id);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }
}

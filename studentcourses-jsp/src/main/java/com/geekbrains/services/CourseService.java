package com.geekbrains.services;

import com.geekbrains.entities.Course;
import com.geekbrains.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

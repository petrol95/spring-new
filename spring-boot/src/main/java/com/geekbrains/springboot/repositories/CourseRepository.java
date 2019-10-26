package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findOneById(Long id);

}

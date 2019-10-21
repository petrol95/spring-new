package com.geekbrains.repositories;

import com.geekbrains.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findOneById(Long id);

}

package com.geekbrains.repositories;

import com.geekbrains.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {

    Student findOneById(Long id);

    @Query(value = "SELECT s.* FROM students_courses sc RIGHT JOIN students s ON sc.student_id = s.id " +
            "GROUP BY s.name ORDER BY count(sc.course_id) DESC", nativeQuery = true)
    List<Student> findAllOrderByCoursesNumber();

}

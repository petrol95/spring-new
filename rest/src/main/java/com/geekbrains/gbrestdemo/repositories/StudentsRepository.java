package com.geekbrains.gbrestdemo.repositories;

import com.geekbrains.gbrestdemo.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
}

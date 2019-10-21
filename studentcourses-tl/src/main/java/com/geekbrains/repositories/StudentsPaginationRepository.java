package com.geekbrains.repositories;

import com.geekbrains.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsPaginationRepository extends PagingAndSortingRepository<Student, Long> {

    @Query(value = "SELECT s from Student s LEFT JOIN s.courses c GROUP BY s.id ORDER BY count (c) DESC")
    Page<Student> findAllOrderByCoursesNumber(Pageable pageable);
}
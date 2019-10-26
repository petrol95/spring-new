package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Student;
import com.geekbrains.springboot.repositories.StudentsPaginationRepository;
import com.geekbrains.springboot.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;
    private StudentsPaginationRepository studentsPaginationRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Autowired
    public void setStudentsPaginationRepository(StudentsPaginationRepository studentsPaginationRepository) {
        this.studentsPaginationRepository = studentsPaginationRepository;
    }

    public StudentsService() {
    }

    public List<Student> getAllStudentsList() {
        return (List<Student>) studentsRepository.findAll();
    }

    public Student getOneById(Long id) {
        return studentsRepository.findOneById(id);
    }

    public List<Student> getAllStudentsOrderByCoursesNumber() {
        return studentsRepository.findAllOrderByCoursesNumber();
    }

    public Page<Student> getAllStudentsOrderByCoursesNumber(Pageable pageable) {
        return studentsPaginationRepository.findAllOrderByCoursesNumber(pageable);
    }

    public void mergeStudent(Student s) {
        studentsRepository.save(s);
    }

    public void removeById(Long id) {
        studentsRepository.deleteById(id);
    }

}

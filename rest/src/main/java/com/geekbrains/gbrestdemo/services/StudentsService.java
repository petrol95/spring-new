package com.geekbrains.gbrestdemo.services;

import com.geekbrains.gbrestdemo.entities.Student;
import com.geekbrains.gbrestdemo.repositories.StudentsRepository;
import com.geekbrains.gbrestdemo.utils.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentsRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student with id = " + id + " not found");
        }
        return student.get();
    }

    public List<Student> getAllStudentsList() {
        return (List<Student>) studentsRepository.findAll();
    }

    public Student saveOrUpdate(Student student) {
        return studentsRepository.save(student);
    }

    public void delete(Long id) {
        Optional<Student> student = studentsRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student with id = " + id + " not found");
        }
        studentsRepository.delete(student.get());
    }
}

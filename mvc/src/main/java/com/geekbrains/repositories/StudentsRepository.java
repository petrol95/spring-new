package com.geekbrains.repositories;

import com.geekbrains.entities.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentsRepository {

    // Заглушка (должны доставать из БД)
    public Student findOneById(Long id) {
        Student student = new Student();
        student.setFirstName("test" + id);
        student.setLastName("test" + id);
        return student;
    }

}

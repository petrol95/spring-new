package com.geekbrains.controllers;

import com.geekbrains.entities.Student;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    public StudentsController() {
    }

    @RequestMapping("/list")
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        model.addAttribute("totalPages", 1);
        model.addAttribute("number", 0);
        model.addAttribute("prev", 0);
        model.addAttribute("next", 0);
        return "students-list";
    }

    @RequestMapping(path = "/{sid}", method = RequestMethod.GET)
    public String showCoursesByStudentId(@PathVariable("sid") int id, Model model) {
        Student student = studentsService.getOneById((long) id);
        model.addAttribute("student", student);
        return "student";
    }

    @RequestMapping("/list/order")
    public String showStudentsListOrderByCoursesNumber(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsOrderByCoursesNumber();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping("/list/order/page/{sid}")
    public String showStudentsListOrderByCoursesNumber(@PathVariable("sid") int pageId, Model model) {
        Page<Student> page = studentsService.getAllStudentsOrderByCoursesNumber(PageRequest.of(pageId, 2));
        List<Student> pageStudents = page.getContent();
        int totalPages = page.getTotalPages();
        int number = page.getNumber();
        int prev = (number == 0) ? 0 : (number - 1);
        int next = (number == totalPages - 1) ? (totalPages - 1) : (number + 1);
        model.addAttribute("studentsList", pageStudents);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("number", number);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
        return "students-list";
    }
}

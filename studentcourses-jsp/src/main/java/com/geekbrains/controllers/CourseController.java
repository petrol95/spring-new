package com.geekbrains.controllers;

import com.geekbrains.entities.Course;
import com.geekbrains.entities.Student;
import com.geekbrains.services.CourseService;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseController() {
    }

    // С помощью GET-запроса выводить информацию по курсу (ID которого должно быть взято из GET-запроса):
    // название, сколько студентов его проходит
    @RequestMapping(path = "/{sid}", method = RequestMethod.GET)
    public String showCourseById(@PathVariable("sid") int id, Model model) {
        Course course = courseService.getCourseById((long) id);
        model.addAttribute("course", course);
        return "course";
    }
}


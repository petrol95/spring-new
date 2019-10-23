package com.geekbrains.controllers;

import com.geekbrains.entities.Course;
import com.geekbrains.entities.Student;
import com.geekbrains.services.CourseService;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    private CourseService courseService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public StudentsController() {
    }

    @RequestMapping("/list")
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentsService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(path = "/{sid}", method = RequestMethod.GET)
    public String showCoursesByStudentId(@PathVariable("sid") int id, Model model) {
        Student student = studentsService.getOneById((long) id);
        List<Course> coursesList = student.getCourses();
        List<Course> missingCourses = courseService.getAllCourses();
        missingCourses.removeAll(coursesList);
        model.addAttribute("student", student);
        model.addAttribute("coursesList", coursesList);
        model.addAttribute("missingCourses", missingCourses);
        return "student";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/add", method=RequestMethod.GET)
    public String showAddForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add-student-form";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String showAddForm(Student student) {
        studentsService.mergeStudent(student);
        return "redirect:/students/list";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/remove/{id}", method=RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long studentId) {
        studentsService.removeById(studentId);
        return "redirect:/students/list";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/{studentId}/unsubscribe/{courseId}", method=RequestMethod.GET)
    public String unsubscribeByStudentIdAndCourseId(@PathVariable(value = "studentId") Long studentId,
                                          @PathVariable(value = "courseId") Long courseId) {
        Student student = studentsService.getOneById(studentId);
        Course course = courseService.getCourseById(courseId);
        List<Course> studentCourses = student.getCourses();
        studentCourses.remove(course);
        student.setCourses(studentCourses);
        studentsService.mergeStudent(student);
        return "redirect:/students/{studentId}";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/{studentId}/subscribe/{courseId}", method=RequestMethod.GET)
    public String subscribeByStudentIdAndCourseId(@PathVariable(value = "studentId") Long studentId,
                                          @PathVariable(value = "courseId") Long courseId) {
        Student student = studentsService.getOneById(studentId);
        Course course = courseService.getCourseById(courseId);
        List<Course> studentCourses = student.getCourses();
        studentCourses.add(course);
        student.setCourses(studentCourses);
        studentsService.mergeStudent(student);
        return "redirect:/students/{studentId}";
    }
}

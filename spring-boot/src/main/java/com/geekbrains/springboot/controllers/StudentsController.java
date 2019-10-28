package com.geekbrains.springboot.controllers;

import com.geekbrains.springboot.entities.Course;
import com.geekbrains.springboot.entities.Student;
import com.geekbrains.springboot.services.CourseService;
import com.geekbrains.springboot.services.StudentsService;
import com.geekbrains.springboot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private static int PAGE_ITEMS = 10;
    private StudentsService studentsService;
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

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

//    @RequestMapping("/list")
//    public String showStudentsList(Model model) {
//        List<Student> allStudents = studentsService.getAllStudentsList();
//        model.addAttribute("studentsList", allStudents);
//        return "students-list";
//    }

//    @RequestMapping("/list/order")
//    public String showStudentsListOrderByCoursesNumber(Model model) {
//        List<Student> allStudents = studentsService.getAllStudentsOrderByCoursesNumber();
//        model.addAttribute("studentsList", allStudents);
//        return "students-list";
//    }

    @RequestMapping("/list/order/page/{sid}")
    public String showStudentsListOrderByCoursesNumber(@PathVariable("sid") int pageId, Model model) {
        Page<Student> page = studentsService.getAllStudentsOrderByCoursesNumber(PageRequest.of(pageId, PAGE_ITEMS));
        List<Student> pageStudents = page.getContent();
        int totalPages = page.getTotalPages();
        int current = page.getNumber();
        int prev, next;
        if (current == 0) {
            prev = current;
            next = current;
        } else if (current == totalPages - 1) {
            prev = current - 1;
            next = current;
        } else {
            prev = current - 1;
            next = current + 1;
        }
        model.addAttribute("studentsList", pageStudents);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("current", current);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);
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
        return "redirect:/students/list/order/page/0";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping("/processAddStudentForm")
    public String processAddStudentForm(@Valid @ModelAttribute("student") Student theStudent,
                                          BindingResult theBindingResult, Model theModel) {
        String studentName = theStudent.getLastName();
        logger.debug("Processing registration form for: " + studentName);
        if (theBindingResult.hasErrors()) {
            return "add-student-form";
        }
        theStudent.setAddedDate(new Date());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = ((UserDetails)auth.getPrincipal()).getUsername();
        theStudent.setAddedUser(userService.findByUserName(currentUserName));
        studentsService.mergeStudent(theStudent);
        logger.debug("Successfully created student: " + studentName);
        return "redirect:/students/list/order/page/0";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path="/remove/{id}", method=RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long studentId) {
        studentsService.removeById(studentId);
        return "redirect:/students/list/order/page/0";
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

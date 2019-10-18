package com.geekbrains.controllers;

import com.geekbrains.repositories.StudentsRepository;
import com.geekbrains.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    StudentsService studentsService;

    @RequestMapping("/")
    public String showHomePage() {

        return "index";
    }
}


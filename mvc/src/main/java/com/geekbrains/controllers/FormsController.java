package com.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormsController {

    @RequestMapping("/showSimpleForm")
    public String showSimpleForm() {
        return "myform";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "myform-result";
    }

}

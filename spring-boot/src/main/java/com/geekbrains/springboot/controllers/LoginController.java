package com.geekbrains.springboot.controllers;

import com.geekbrains.springboot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "modern-login";
    }

    @GetMapping("/accessDenied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }

//    @GetMapping("/ohSh")
//    public String ohSh() {
//        return "plain-login";
//    }

    @GetMapping("/dropUser")
    public String dropUser() {
        userRepository.deleteById(2L);
        return "login";
    }
}

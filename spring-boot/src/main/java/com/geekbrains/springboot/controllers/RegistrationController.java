package com.geekbrains.springboot.controllers;

import com.geekbrains.springboot.entities.SystemUser;
import com.geekbrains.springboot.entities.User;
import com.geekbrains.springboot.services.RoleService;
import com.geekbrains.springboot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("systemUser", new SystemUser());
        theModel.addAttribute("allRoles", roleService.getAllRoles());
        return "registration-form";
    }

    // Binding Result после @ValidModel !!!
    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(@Valid @ModelAttribute("systemUser") SystemUser theSystemUser,
                                          BindingResult theBindingResult, Model theModel) {
        String userName = theSystemUser.getUserName();
        logger.debug("Processing registration form for: " + userName);
        if (theBindingResult.hasErrors()) {
            theModel.addAttribute("allRoles", roleService.getAllRoles());
            return "registration-form";
        }
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("systemUser", new SystemUser());
            theModel.addAttribute("allRoles", roleService.getAllRoles());
            theModel.addAttribute("registrationError", "User name already exists.");
            logger.debug("User name already exists.");
            return "registration-form";
        }
        userService.save(theSystemUser);
        logger.debug("Successfully created user: " + userName);
        return "registration-confirmation";
    }
}

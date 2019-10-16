package com.geekbrains.controllers;

import com.geekbrains.dao.JdbcTemplateDiskDao;
import com.geekbrains.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    JdbcTemplateDiskDao jdbcTemplateDiskDao;

    @RequestMapping("/")
    public String showHomePage() {
        Disk disk = new Disk();
        disk.setId(0L);
        disk.setTitle("Hello");
        jdbcTemplateDiskDao.insert(disk);
        System.out.println(jdbcTemplateDiskDao.findTitleById(1L));
        return "index";
    }
}

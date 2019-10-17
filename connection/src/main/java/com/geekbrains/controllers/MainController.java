package com.geekbrains.controllers;

import com.geekbrains.dao.DiskHibDao;
import com.geekbrains.dao.JdbcTemplateDiskDao;
import com.geekbrains.entities.Disk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//    @Autowired
//    JdbcTemplateDiskDao jdbcTemplateDiskDao;

    @Autowired
    DiskHibDao diskHibDao;

    @RequestMapping("/")
    public String showHomePage() {

        // Jdbc Template
//        Disk disk = new Disk();
//        disk.setId(0L);
//        disk.setTitle("Hello");
//        jdbcTemplateDiskDao.insert(disk);
//        System.out.println(jdbcTemplateDiskDao.findTitleById(1L));

        // Hibernate
        System.out.println("by ID: " + diskHibDao.findById(1L));
        System.out.println("All: ");
        System.out.println(diskHibDao.findAll());

        return "index";
    }
}

package com.geekbrains.controllers;

import com.geekbrains.dao.DiskHibDao;
import com.geekbrains.dao.DiskHibPaginationRepository;
import com.geekbrains.dao.DiskHibRepository;
import com.geekbrains.dao.JdbcTemplateDiskDao;
import com.geekbrains.entities.Disk;
import com.geekbrains.entities.DiskHib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

//    @Autowired
//    JdbcTemplateDiskDao jdbcTemplateDiskDao;

//    @Autowired
//    DiskHibDao diskHibDao;

    @Autowired
    DiskHibRepository diskHibRepository;

    @Autowired
    DiskHibPaginationRepository diskHibPaginationRepository;

    @RequestMapping("/")
    public String showHomePage() {

        // Jdbc Template
//        Disk disk = new Disk();
//        disk.setId(0L);
//        disk.setTitle("Hello");
//        jdbcTemplateDiskDao.insert(disk);
//        System.out.println(jdbcTemplateDiskDao.findTitleById(1L));

        // Hibernate
//        System.out.println("by ID: " + diskHibDao.findById(1L));
//        System.out.println("All: ");
//        System.out.println(diskHibDao.findAll());

        // Spring Data
        System.out.println(diskHibRepository.findAll());
        System.out.println(diskHibRepository.count());
        System.out.println(diskHibRepository.findById(2L));
        System.out.println(diskHibRepository.findAllByProducedYearAndId(2016, 4L));

        Page<DiskHib> page = diskHibPaginationRepository.findAll(PageRequest.of(0,2));
        System.out.println("Pagination: " + page.getContent());

        return "index";
    }
}

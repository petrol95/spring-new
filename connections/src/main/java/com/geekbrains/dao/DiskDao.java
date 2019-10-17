package com.geekbrains.dao;

import com.geekbrains.entities.Disk;

public interface DiskDao {
    void insert(Disk disk);
    String findTitleById(Long id);
}

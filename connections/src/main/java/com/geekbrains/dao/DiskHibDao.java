package com.geekbrains.dao;

import com.geekbrains.entities.DiskHib;

import java.util.List;

public interface DiskHibDao {
    DiskHib findById(Long id);

    List<DiskHib> findAll();
}

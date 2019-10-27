package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Role;
import com.geekbrains.springboot.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleService() {
    }

    public Collection<Role> getAllRoles() {
        return (Collection<Role>) roleRepository.findAll();
    }
}

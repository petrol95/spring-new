package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.Role;
import com.geekbrains.springboot.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleService() {
    }

    public List<Role> getAllRoles() {
        return (List)roleRepository.findAll();
    }

}

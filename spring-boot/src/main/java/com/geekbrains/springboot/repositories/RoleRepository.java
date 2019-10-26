package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findOneByName(String theRoleName);
}
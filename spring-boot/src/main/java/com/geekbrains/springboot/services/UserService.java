package com.geekbrains.springboot.services;

import com.geekbrains.springboot.entities.SystemUser;
import com.geekbrains.springboot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String userName);
    void save(SystemUser systemUser);
}

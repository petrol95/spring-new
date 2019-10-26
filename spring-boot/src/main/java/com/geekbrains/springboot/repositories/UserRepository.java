package com.geekbrains.springboot.repositories;

import com.geekbrains.springboot.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUserName(String userName);
}

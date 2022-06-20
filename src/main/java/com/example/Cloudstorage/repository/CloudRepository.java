package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.userdata.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CloudRepository extends CrudRepository<User, Long> {
    User findByUsernameAndPassword(String userName,  String password);

    User findByUsername(String userName);
}

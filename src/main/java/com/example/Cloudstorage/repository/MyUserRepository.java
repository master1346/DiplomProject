package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.userdata.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);
}

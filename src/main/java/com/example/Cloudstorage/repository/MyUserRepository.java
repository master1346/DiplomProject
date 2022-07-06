package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.userdata.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByPassword(String password);

}

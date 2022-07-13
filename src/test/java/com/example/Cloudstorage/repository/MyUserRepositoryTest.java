package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.CloudstorageApplicationTests;
import com.example.Cloudstorage.userdata.MyUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MyUserRepositoryTest extends CloudstorageApplicationTests {

    @Autowired
    private MyUserRepository myUserRepository;

    @Test
    void findByUsername() {
        Optional<MyUser> actual = myUserRepository.findByUsername("usertest");
        assertNotNull(actual);
    }

    @Test
    void existsByUsername() {
        boolean actual = myUserRepository.existsByUsername("usertest");
        assertTrue(actual);
    }

    @Test
    void existsByPassword() {
        boolean actual = myUserRepository.existsByPassword("passwordtest");
        assertTrue(actual);
    }
}
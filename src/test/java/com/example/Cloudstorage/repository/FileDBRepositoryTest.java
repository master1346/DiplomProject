package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.CloudstorageApplicationTests;
import com.example.Cloudstorage.db.model.FileDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;


class FileDBRepositoryTest  extends CloudstorageApplicationTests {

    @Autowired
    private FileDBRepository fileDBRepository;

    private FileDB fileDB;

    @BeforeEach
    void setUp() {
        fileDB = new FileDB("Test.txt", "text/plain", "Test Correct".getBytes(StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        fileDB = null;
    }

    @Test
    void testFindByName() {
        FileDB fileDBTest = fileDBRepository.findByName("hello.txt");
        assertNotNull(fileDBTest);
    }

    @Test
    void testSave(){
        FileDB fileDBTest = fileDBRepository.save(fileDB);
        assertNotNull(fileDBTest);
    }

    @Test
    void testDelete() {
        fileDBRepository.delete(fileDBRepository.findByName("hello.txt"));
        FileDB fileDBTest = fileDBRepository.findByName("hello.txt");
        assertNull(fileDBTest);
    }

}
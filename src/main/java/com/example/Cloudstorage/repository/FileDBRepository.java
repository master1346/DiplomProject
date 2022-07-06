package com.example.Cloudstorage.repository;

import com.example.Cloudstorage.db.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    FileDB findByName(String filename);
}
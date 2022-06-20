package com.example.Cloudstorage.service;

import java.io.IOException;
import java.util.stream.Stream;

import com.example.Cloudstorage.db.model.FileDB;
import com.example.Cloudstorage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private final FileDBRepository fileDBRepository;

    @Autowired
    public FileStorageService(FileDBRepository fileDBRepository) {
        this.fileDBRepository = fileDBRepository;
    }

    public FileDB store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public String deleteFile(String id) {
        if(fileDBRepository.existsById(id)) {
            String fileDBDeleteName = fileDBRepository.getById(id).getName();
            fileDBRepository.deleteById(id);
            return fileDBDeleteName;
        } else {
            return "File with id " + id + " not exist";
        }
    }

    public String updateFile(String id, String newName){
        if(fileDBRepository.existsById(id)) {
            FileDB fileDB = getFile(id);
            fileDB.setName(newName);
            fileDBRepository.save(fileDB);
            return "Update name file with id " + id;
        } else{
            return "File with id " + id + " not exist";
        }
    }

}

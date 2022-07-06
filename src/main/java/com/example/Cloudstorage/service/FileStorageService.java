package com.example.Cloudstorage.service;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

import com.example.Cloudstorage.components.File;
import com.example.Cloudstorage.db.model.FileDB;
import com.example.Cloudstorage.message.ResponseMessage;
import com.example.Cloudstorage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(FileDB);
    }

    public File download(String filename){
        FileDB fileDB = fileDBRepository.findByName(filename);
        return new File(fileDB.getType(), new String(fileDB.getData()));
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void deleteFile(String filename) {
            fileDBRepository.delete(fileDBRepository.findByName(filename));
    }

    public ResponseEntity<ResponseMessage> updateFile(String oldName, UpdateFileName newName) {
        FileDB fileDB = fileDBRepository.findByName(oldName);

        if (fileDB == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseMessage("File not found"));

        fileDB.setName(newName.getFilename());
        fileDBRepository.save(fileDB);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("File update" ));
    }

}

package com.example.Cloudstorage.controller;

import com.example.Cloudstorage.components.File;
import com.example.Cloudstorage.components.Error;
import com.example.Cloudstorage.message.ResponseMessage;
import com.example.Cloudstorage.model.FileProperty;
import com.example.Cloudstorage.service.FileStorageService;
import com.example.Cloudstorage.service.UpdateFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class CloudController {

    private final FileStorageService storageService;

    @Autowired
    public CloudController( FileStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/file")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            storageService.store(file);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success upload: " + file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Error upload file: ", 500));
        }
    }

    @GetMapping("/file")
    public ResponseEntity<Object> downloadFile(@RequestParam("filename") String filename ){
        File fileDownload = storageService.download(filename);
        if(fileDownload == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("Error input data ", 400));
        storageService.deleteFile(filename);
        return ResponseEntity.status(HttpStatus.OK).body(fileDownload);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FileProperty[]> getListFiles(@RequestParam("limit") int limit) {
        List<FileProperty> files = storageService.getAllFiles().map(dbFile -> new FileProperty(
                dbFile.getName(),
                dbFile.getData().length)).collect(Collectors.toList());

        FileProperty[] fileListResponse = new FileProperty[limit];

        for(int i = 0; i < limit; i++){
            if(i >= files.size()){
                break;
            }
            fileListResponse[i] = files.get(i);
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileListResponse);
    }

    @DeleteMapping("/file")
    public ResponseEntity<ResponseMessage> deleteFile(@RequestParam String filename){
        storageService.deleteFile(filename);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Status delete: " + filename));
    }

    @PutMapping("/file")
    public ResponseEntity<ResponseMessage> updateFile(@RequestParam("filename") String oldName, @RequestBody UpdateFileName newName){
        return storageService.updateFile(oldName, newName);
    }
}

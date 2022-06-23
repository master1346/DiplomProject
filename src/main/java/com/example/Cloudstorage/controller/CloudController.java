package com.example.Cloudstorage.controller;

import com.example.Cloudstorage.message.ResponseMessage;
import com.example.Cloudstorage.repository.CloudRepository;
import com.example.Cloudstorage.service.FileStorageService;
import com.example.Cloudstorage.service.UserService;
import com.example.Cloudstorage.config.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cloud")
public class CloudController {

    private final CloudRepository cloudRepository;
    private final FileStorageService storageService;
    private final UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public CloudController(CloudRepository cloudRepository, FileStorageService storageService, UserService userService) {
        this.cloudRepository = cloudRepository;
        this.storageService = storageService;
        this.userService = userService;
    }
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Welcome home", HttpStatus.OK);
    }
    @GetMapping("/user")
    public String user(){
        return ("Welcome user");
    }

    @GetMapping("/test")
    public String hello(){
        return ("Welcome test");
    }

    /*
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> login(@RequestBody AuthRequest user){
       // User userAnswer = userService.login(user);
        System.out.println(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Ok"));

        /* if(Objects.isNull(userAnswer)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Пользователь не найден"));
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Окей"));
        }
        */
    /*
    }
    @PostMapping(path = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> logout(@RequestBody AuthRequest user) {
        return null;
    }
    */
    /*
    @PostMapping("/file_upload")
    public String addFiles(
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            Path path = (Path) Paths.get(uploadPath + "/" + file.getOriginalFilename() );
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return "File " + file.getOriginalFilename() + " upload on server";
        }

      return "Errors: File not exist";
    }
     */


/*
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();
            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/files_del/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String id){
        String statusDelete = storageService.deleteFile(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Status delete: " + statusDelete));
    }
/*
    @PutMapping("/files_rename/{id}")
    public ResponseEntity<ResponseMessage> updateFile(@RequestBody Object object, @PathVariable String id);
        String statusDelete = storageService.updateFile(id, newName);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(statusDelete));
    }
*/
}

package com.example.Cloudstorage.service;

import com.example.Cloudstorage.components.File;
import com.example.Cloudstorage.db.model.FileDB;
import com.example.Cloudstorage.message.ResponseMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class FileStorageServiceTest {

    @Mock
    private FileStorageService fileStorageService;

    private FileDB fileDB;
    private MockMultipartFile multipartFile;
    private UpdateFileName updateFileName;

    @BeforeEach
    public void setUp() throws IOException {
        multipartFile =   new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());

        fileDB = new FileDB(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
        updateFileName = new UpdateFileName(fileDB.getName() + "_new");

    }
    @AfterEach
    public void tearDown() {
        multipartFile = null;
        fileDB = null;
    }

  @Test
    void store() throws IOException {
        when(fileStorageService.store(multipartFile)).thenReturn(fileDB);
        FileDB fileDBTest = fileStorageService.store(multipartFile);
        Assertions.assertNotNull(fileDBTest);
    }

   @Test
    void download() {
        when(fileStorageService.download(fileDB.getName())).thenReturn(new File(fileDB.getType(), new String(fileDB.getData())));
        File fileDownloadTest = fileStorageService.download(fileDB.getName());
        assertEquals(fileDownloadTest.getFile(), "Hello, World!");
    }

    @Test
    void updateFile() {
        when(fileStorageService.updateFile(fileDB.getName(), updateFileName)).thenReturn(new ResponseEntity<>(new ResponseMessage("File update" ), HttpStatus.OK));
        ResponseEntity<ResponseMessage> expected = fileStorageService.updateFile(fileDB.getName(), updateFileName);
        assertEquals(HttpStatus.OK, expected.getStatusCode());
    }
}
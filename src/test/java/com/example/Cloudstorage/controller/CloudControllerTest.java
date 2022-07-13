package com.example.Cloudstorage.controller;

import com.example.Cloudstorage.service.FileStorageService;
import com.example.Cloudstorage.service.UpdateFileName;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class CloudControllerTest {

    @Autowired
    private CloudController controllerCloud;

    @MockBean
    private FileStorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controllerCloud).isNotNull();
    }

    @Test
    void uploadFile() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(multipart("/file").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void downloadFile() throws Exception {
        this.mockMvc.perform(get("/file")
                        .param("filename", "test.txt"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getListFiles() throws Exception {
        this.mockMvc.perform(get("/list")
                        .param("limit", "3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteFile() throws Exception{
        this.mockMvc.perform(delete("/file")
                .param("filename", "deleteName.txt"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateFile() throws Exception {
        this.mockMvc.perform(put("/file")
                .param("filename", "oldName.txt")
                .content(objectMapper.writeValueAsString(new UpdateFileName("newName.txt")))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
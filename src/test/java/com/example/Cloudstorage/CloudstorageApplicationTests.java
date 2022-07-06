package com.example.Cloudstorage;

import com.example.Cloudstorage.controller.CloudController;
import com.example.Cloudstorage.db.model.FileDB;
import com.example.Cloudstorage.service.FileStorageService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
class CloudstorageApplicationTests {

 	@Autowired
	private CloudController cloudController;

	@Test
	void contextLoads() throws Exception {
		assertThat(cloudController).isNotNull();
	}


}

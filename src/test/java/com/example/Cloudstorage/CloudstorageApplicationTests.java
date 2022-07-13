package com.example.Cloudstorage;

import com.example.Cloudstorage.initializer.MySQLInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Sql("/sql/data.sql")
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {
        MySQLInitializer.Initializer.class
})
public class CloudstorageApplicationTests {

    @BeforeAll
    static void init(){
        MySQLInitializer.container.start();
    }

}

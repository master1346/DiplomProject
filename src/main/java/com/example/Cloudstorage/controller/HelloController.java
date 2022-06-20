package com.example.Cloudstorage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello";
    }
    // сюда доступ разрешен только user и admin
    @GetMapping("/user")
    public String user() {
        return "User";
    }
    // сюда доступ разрешен только admin
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}

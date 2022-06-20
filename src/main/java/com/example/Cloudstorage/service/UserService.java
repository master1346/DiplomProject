package com.example.Cloudstorage.service;

import com.example.Cloudstorage.repository.CloudRepository;
import com.example.Cloudstorage.userdata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private CloudRepository cloudRepository;

    public User login(User user){
        return cloudRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

}

package com.example.Cloudstorage.service;

import com.example.Cloudstorage.repository.MyUserRepository;
import com.example.Cloudstorage.userdata.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

   private final MyUserRepository userRepository;

   @Autowired
    public UserDetailsServiceImpl(MyUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}

package com.example.Cloudstorage.controller;

import com.example.Cloudstorage.config.JWTUtil;
import com.example.Cloudstorage.config.AuthRequest;
import com.example.Cloudstorage.config.AuthResponse;
import com.example.Cloudstorage.config.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtTokenUtil;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Login createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return new Login(jwt);
    }
}

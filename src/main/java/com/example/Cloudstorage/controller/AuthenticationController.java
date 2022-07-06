package com.example.Cloudstorage.controller;

import com.example.Cloudstorage.auth.JwtResponse;
import com.example.Cloudstorage.auth.JwtUtils;
import com.example.Cloudstorage.auth.AuthRequest;
import com.example.Cloudstorage.components.Login;
import com.example.Cloudstorage.message.ResponseMessage;
import com.example.Cloudstorage.repository.MyUserRepository;
import com.example.Cloudstorage.repository.RoleRepository;
import com.example.Cloudstorage.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationController(AuthenticationManager authenticationManager, MyUserRepository myUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                                      .authenticate(
                                           new UsernamePasswordAuthenticationToken(
                                                   authRequest.getLogin(),
                                                   authRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new Login(jwt));

      /*  return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(), roles));



       */
    }
    @GetMapping("/login")
    public ResponseEntity<ResponseMessage> loginLogout(){
       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success logout"));
    }
    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(@RequestBody String authToken) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success logout"));
    }
}

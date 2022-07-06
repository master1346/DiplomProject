package com.example.Cloudstorage.auth;

import java.util.List;

public class JwtResponse {
    private String jwt;
    Long id;
    private String username;
    private List<String> roles;

    public JwtResponse(String jwt, Long id, String username, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

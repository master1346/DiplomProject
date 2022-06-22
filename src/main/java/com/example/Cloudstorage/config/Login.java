package com.example.Cloudstorage.config;

public class Login {
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Login(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "auth-token='" + authToken;
    }
}

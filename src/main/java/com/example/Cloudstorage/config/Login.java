package com.example.Cloudstorage.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Login {

    private String authToken;

    @JsonProperty("auth-token")
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

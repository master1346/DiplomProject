package com.example.Cloudstorage.config;

public class AuthResponse {
    private String authtoken;

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public AuthResponse(String authtoken) {
        this.authtoken = authtoken;
    }

    /*  private String jwtToken;

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

   */
}

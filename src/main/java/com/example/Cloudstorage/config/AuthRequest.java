package com.example.Cloudstorage.config;

import java.util.Objects;

public class AuthRequest {
        private String login;
        private String password;

    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AuthRequest() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthRequest authRequest = (AuthRequest) o;
        return Objects.equals(login, authRequest.login) && Objects.equals(password, authRequest.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoad{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

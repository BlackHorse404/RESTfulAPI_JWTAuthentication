package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Account {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "{"
                + "\"username\": \"" + username + '\"'
                + ", \"password\": \"" + password + '\"' + '}';
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

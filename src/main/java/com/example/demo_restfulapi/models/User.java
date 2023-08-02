package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class User {
    private String username;
    private String password;
    private boolean enable;

    public User() {
    }

    public User(String username, String password, boolean enable) {
        this.username = username;
        this.password = password;
        this.enable = enable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnable() {
        return enable;
    }

    public boolean Auth(User user){
        return username.equals(user.username) && password.equals(user.password) ? true:false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enable=" + enable +
                '}';
    }
}

package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class JwtResponse {

    private int status;
    private String username;
    private String token;
    private String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public int getStatus() {
        return status;
    }

    public JwtResponse(String username, String token, String type, int status) {
        this.username = username;
        this.token = token;
        this.tokenType = type;
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

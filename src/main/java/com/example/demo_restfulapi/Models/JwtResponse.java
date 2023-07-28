package com.example.demo_restfulapi.Models;

public class JwtResponse {

    private String status;
    private String username;
    private String token;
    private String tokenType;

    public String getTokenType() {
        return tokenType;
    }

    public String getStatus() {
        return status;
    }

    public JwtResponse(String username, String token, String type, String status) {
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

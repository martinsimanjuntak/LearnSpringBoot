package com.example.hellospring.Auth;

public class AuthResponse {
    private String email;
    private String accessToken;

    public AuthResponse() {
    }

    public AuthResponse(String email, String accesToken) {
        this.email = email;
        this.accessToken = accesToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccesToken() {
        return accessToken;
    }

    public void setAccessToken(String accesToken) {
        this.accessToken = accesToken;
    }
}

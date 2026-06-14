package com.ferreteriapsa.logistica.auth.dto.response;

public class Auth {
    private String token;
    private String refreshToken;

    public Auth(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }
    public String getRefreshToken(){
        return refreshToken;
    }
}

package com.consorcio.servicios.Security.Dto;

import java.util.List;

public class JwtDto {

    private String token;
    private List<String> authorities;
    private boolean isLogin;

    public JwtDto(String token, List<String> authorities, boolean isLogin) {
        this.token = token;
        this.authorities = authorities;
        this.isLogin = isLogin;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}

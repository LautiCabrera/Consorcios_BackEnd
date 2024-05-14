package com.consorcio.servicios.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
    
    private String token;
    private Collection <? extends GrantedAuthority > authorities;

    public JwtDto(String token, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
}
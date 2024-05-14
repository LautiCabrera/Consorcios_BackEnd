package com.consorcio.servicios.Security.Auth;

import com.consorcio.servicios.Security.Dto.JwtDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping(value = "/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest request){
        JwtDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.userRegister(request));
    }

}
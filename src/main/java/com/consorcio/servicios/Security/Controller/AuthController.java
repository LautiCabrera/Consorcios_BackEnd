package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Security.Entity.AuthResponse;
import com.consorcio.servicios.Security.Entity.RegisterRequest;
import com.consorcio.servicios.Security.Entity.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/user/register")
    public AuthResponse userRegister(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.userRegister(request);
        return response;
    }

    @PostMapping(value = "/admin/register")
    public AuthResponse adminRegister(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.adminRegister(request);
        return response;
    }

}
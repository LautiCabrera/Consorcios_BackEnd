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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest request) {
        JwtDto response = authService.login(request);
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

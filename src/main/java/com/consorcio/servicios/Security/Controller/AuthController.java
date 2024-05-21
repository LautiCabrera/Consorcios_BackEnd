package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Security.Entity.AuthResponse;
import com.consorcio.servicios.Security.Entity.RegisterRequest;
import com.consorcio.servicios.Security.Entity.LoginRequest;
import com.consorcio.servicios.Security.Dto.JwtDto;
import com.consorcio.servicios.Security.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService; 

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
    
     @GetMapping("/email/send")
    public ResponseEntity <?> sendEmail() {
        emailService.sendEmail();
        return new ResponseEntity("Correo enviado con éxito", HttpStatus.OK);
    }
    
}
package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.consorcio.servicios.Security.Dto.AuthResponseDto;
import com.consorcio.servicios.Security.Dto.ForgotPassDto;
import com.consorcio.servicios.Security.Dto.LoginRequestDto;
import com.consorcio.servicios.Security.Dto.RecoverPassRequestDto;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.RecoverPassService;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final RecoverPassService recoverPassService;
    @Value("${mail.recover.expiration}")
    long expiration;

    @PostMapping(value = "/login")
    public AuthResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    @PostMapping(value = "/user/register")
    public AuthResponseDto userRegister(@RequestBody RegisterRequestDto request) {
        return authService.register(request, Role.ROLE_USER);
    }

    @PostMapping(value = "/admin/register")
    public AuthResponseDto adminRegister(@RequestBody RegisterRequestDto request) {
        return authService.register(request, Role.ROLE_ADMIN);
    }

    @PostMapping("/forgot")
    public String forgotPassword(@RequestBody ForgotPassDto request) {
        return recoverPassService.forgotPassword(request);
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestBody RecoverPassRequestDto request) {
        return recoverPassService.resetPassword(request);
    }

}
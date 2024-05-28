package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.consorcio.servicios.Security.Dto.*;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.RecoverPassService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final RecoverPassService recoverPassService;

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
    public MessageDto forgotPassword(@RequestBody ForgotPassDto request) {
        return recoverPassService.forgotPassword(request);
    }

    @PostMapping("/reset")
    public MessageDto resetPassword(@RequestBody RecoverPassRequestDto request) {
        return recoverPassService.resetPassword(request);
    }

}
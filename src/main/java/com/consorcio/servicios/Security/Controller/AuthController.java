package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.consorcio.servicios.Security.Dto.*;
import com.consorcio.servicios.Security.Service.RecoverPassService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RecoverPassService recoverPassService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot")
    public ResponseEntity<MessageOkDto> forgotPassword(@RequestBody ForgotPassDto request) {
        recoverPassService.forgotPassword(request);
        return ResponseEntity.ok(new MessageOkDto("Correo electrónico de restablecimiento de contraseña enviado"));
    }

    @PostMapping("/reset")
    public ResponseEntity<MessageOkDto> resetPassword(@RequestBody RecoverPassRequestDto request) {
        recoverPassService.resetPassword(request);
        return ResponseEntity.ok(new MessageOkDto("Contraseña cambiada con exito"));
    }

}
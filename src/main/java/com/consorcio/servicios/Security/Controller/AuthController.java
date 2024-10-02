package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.*;
import com.consorcio.servicios.Security.Service.RecoverPassService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RecoverPassService recoverPassService;

    @PostMapping("/login")
    public WebApiResponse<AuthResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            AuthResponseDto response = authService.login(request);
            return WebApiResponse.success(response, "Inicio de sesión exitoso", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error en el inicio de sesión", e.getMessage(),
                    HttpStatus.UNAUTHORIZED.value());
        }
    }

    @PostMapping("/forgot")
    public WebApiResponse<Void> forgotPassword(@RequestBody ForgotPassDto request) {
        try {
            recoverPassService.forgotPassword(request);
            return WebApiResponse.success(null, "Correo electrónico de restablecimiento de contraseña enviado",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al enviar el correo", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/reset")
    public WebApiResponse<Void> resetPassword(@RequestBody RecoverPassRequestDto request) {
        try {
            recoverPassService.resetPassword(request);
            return WebApiResponse.success(null, "Contraseña cambiada con éxito", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al cambiar la contraseña", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
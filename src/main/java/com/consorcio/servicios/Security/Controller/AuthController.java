package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.*;
import com.consorcio.servicios.Security.Service.RecoverPassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;

@RestController
@Tag(name = "Modulo de Autenticación", description = "Operaciones relacionadas con la autenticación de usuarios, como el inicio de sesión, recuperación de contraseña y restablecimiento de contraseña.")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RecoverPassService recoverPassService;

    @Operation(summary = "Inicio de sesión", description = "Permite a los usuarios iniciar sesión en el sistema con sus credenciales.")
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

    @Operation(summary = "Recuperar contraseña", description = "Envía un correo electrónico con instrucciones para restablecer la contraseña del usuario.")
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

    @Operation(summary = "Restablecer contraseña", description = "Permite al usuario cambiar su contraseña después de haber recibido las instrucciones por correo.")
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
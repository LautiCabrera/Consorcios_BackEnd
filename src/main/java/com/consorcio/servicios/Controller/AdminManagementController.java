package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Modulo de Administradores", description = "Operaciones relacionadas con la gestión de administradores, como el registro de nuevos administradores.")
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminManagementController {

    private final AuthService authService;

    @Operation(summary = "Registrar Administrador", description = "Registra un nuevo administrador en el sistema.")
    @PostMapping("/register")
    public WebApiResponse<Void> adminRegister(@RequestBody RegisterRequestDto request) {
        try {
            authService.register(request, Role.ROLE_ADMIN);
            return WebApiResponse.success(null, "Administrador registrado exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al registrar administrador", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
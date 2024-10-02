package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Service.ReadingService;
import com.consorcio.servicios.Service.UserManagementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/operator")
public class AdminManagementController {

    @Autowired
    private UserManagementService userManagementService;

    private final AuthService authService;

    @Autowired
    private ReadingService readingService;

    // Gestion de usuarios

    @GetMapping
    public WebApiResponse<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userManagementService.getAllUsers();
            return WebApiResponse.success(users, "Usuarios obtenidos exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener usuarios", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register")
    public WebApiResponse<Void> userRegister(@RequestBody RegisterRequestDto request) {
        try {
            authService.register(request, Role.ROLE_USER);
            return WebApiResponse.success(null, "Registro exitoso", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al registrar usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update")
    public WebApiResponse<Void> updateUser(@RequestParam("idUser") Long idUser, @RequestBody UserDto userDto) {
        try {
            userManagementService.updateUser(idUser, userDto);
            return WebApiResponse.success(null, "Actualización exitosa", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/toggle-activation")
    public WebApiResponse<Void> toggleActivation(@RequestParam("id") Long id,
            @RequestParam("status") UserStatus status) {
        try {
            userManagementService.changeUserStatus(id, status);
            return WebApiResponse.success(null, "Cambio de estado exitoso", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al cambiar estado del usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestión de lecturas del usuario

    @GetMapping("/readings")
    public WebApiResponse<List<Reading>> getAllReadings() {
        try {
            List<Reading> readings = readingService.getAllReadings();
            return WebApiResponse.success(readings, "Lecturas obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener lecturas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/readings")
    public WebApiResponse<Void> createReading(@RequestBody Reading reading) {
        try {
            readingService.createReading(reading);
            return WebApiResponse.success(null, "Lectura creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear lectura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/readings/{id}")
    public WebApiResponse<Void> updateReading(@PathVariable long idReading, @RequestBody Reading reading) {
        try {
            readingService.updateReading(idReading, reading);
            return WebApiResponse.success(null, "Lectura actualizada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar lectura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestión de residencia del usuario

}
package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Security.Dto.MessageOkDto;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/super-auth")
@RequiredArgsConstructor
public class SuperAdminManagementController {
    
    private final AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<MessageOkDto> adminRegister(@RequestBody RegisterRequestDto request) {
        authService.register(request, Role.ROLE_ADMIN);
        return ResponseEntity.ok(new MessageOkDto("Administrador registrado exitosamente"));
    }
    
}

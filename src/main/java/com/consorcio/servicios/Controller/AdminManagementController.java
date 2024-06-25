package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Dto.MessageOkDto;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Service.UserManagementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AdminManagementController {
    
    @Autowired
    private UserManagementService userManagementService;
    private final AuthService authService;
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> activeUsers = userManagementService.getAllUsers();
        return ResponseEntity.ok(activeUsers);
    }
    
    @PostMapping("/register")
    public ResponseEntity<MessageOkDto> userRegister(@RequestBody RegisterRequestDto request) {
        authService.register(request, Role.ROLE_USER);
        return ResponseEntity.ok(new MessageOkDto("Usuario registrado exitosamente"));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageOkDto> updateUser(@RequestParam("id") int id, @RequestBody UserDto userDto) {
        userManagementService.updateUser(id, userDto);
        return ResponseEntity.ok(new MessageOkDto("Usuario actualizado exitosamente"));
    }

    @PutMapping("/toggle-activation")
    public ResponseEntity<MessageOkDto> toggleActivation(@RequestParam("id") int id, @RequestParam("status") UserStatus status) {
        userManagementService.changeUserStatus(id, status);
        String message = (status == UserStatus.ACTIVE) ? "Usuario activado exitosamente" : "Usuario desactivado exitosamente";
        return ResponseEntity.ok(new MessageOkDto(message));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUsers(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "dni", required = false) Integer dni) {
        List<UserDto> activeUsers;

        if (name != null) {
            activeUsers = userManagementService.searchActiveUserByName(name);
        } else if (dni != null) {
            activeUsers = userManagementService.searchActiveUserByDni(dni);
        } else {
            throw new IllegalArgumentException("Debe proporcionar un parámetro de búsqueda: name o dni");
        }
        
        return ResponseEntity.ok(activeUsers);
    }
    
}
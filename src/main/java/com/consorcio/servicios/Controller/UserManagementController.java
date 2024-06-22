package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Security.Dto.AuthResponseDto;
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
public class UserManagementController {
    
    @Autowired
    private UserManagementService userManagementService;
    private final AuthService authService;
    
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDto> userRegister(@RequestBody RegisterRequestDto request) {
        AuthResponseDto response = authService.register(request, Role.ROLE_USER);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User updatedUser = userManagementService.updateUser(id, userDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable int id) {
        userManagementService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDto>> getAllActiveUsers() {
        List<UserDto> activeUsers = userManagementService.getAllActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }
    
}
package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Security.Entity.AuthResponse;
import com.consorcio.servicios.Security.Entity.RegisterRequest;
import com.consorcio.servicios.Security.Entity.LoginRequest;
import com.consorcio.servicios.Security.Dto.JwtDto;
import com.consorcio.servicios.Security.Service.EmailService;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginRequest request) {
        JwtDto response = authService.login(request);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/user/register")
    public AuthResponse userRegister(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.userRegister(request);
        return response;
    }

    @PostMapping(value = "/admin/register")
    public AuthResponse adminRegister(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.adminRegister(request);
        return response;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Optional<User> userOptional = userRepository.findByUsername(email);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        emailService.sendResetPasswordEmail(email, token);

        return ResponseEntity.ok("Correo electrónico de restablecimiento de contraseña enviado");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        Optional<User> userOptional = userRepository.findByResetToken(token);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);

        return ResponseEntity.ok("Contraseña modificada con exito");
    }
}

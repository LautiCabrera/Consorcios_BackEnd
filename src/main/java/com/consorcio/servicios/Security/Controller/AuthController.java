package com.consorcio.servicios.Security.Controller;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Security.Repository.RecoverPassRepository;
import com.consorcio.servicios.Security.Service.EmailService;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final EmailService emailService;
    @Autowired
    private RecoverPassRepository recoverPassRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${mail.recover.expiration}")
    long expiration;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Optional<User> userOptional = recoverPassRepository.findByUsername(email);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(expiration));
        recoverPassRepository.save(user);
        emailService.sendResetPasswordEmail(email, token);

        return ResponseEntity.ok("Correo electrónico de restablecimiento de contraseña enviado");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        Optional<User> userOptional = recoverPassRepository.findByResetToken(token);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalido");
        }

        User user = userOptional.get();

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este link expiro");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiration(null);
        recoverPassRepository.save(user);

        return ResponseEntity.ok("Contraseña modificada con exito");
    }

}

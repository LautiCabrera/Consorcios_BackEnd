package com.consorcio.servicios.Security.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Dto.*;
import com.consorcio.servicios.Security.Repository.RecoverPassRepository;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.access.AccessDeniedException;

@Service
public class RecoverPassService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecoverPassRepository recoverPassRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${spring.mail.username}")
    String mailSend;
    @Value("${mail.recover.expiration}")
    long expiration;

    public void sendResetPasswordEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSend);
        message.setTo(email);
        message.setSubject("Cambio de contraseña");
        message.setText("Para restablecer su contraseña, haga clic en el siguiente enlace: "
                + "http://localhost:5173/changeCode?token=" + token);
        javaMailSender.send(message);
    }

    public void forgotPassword(ForgotPassDto request) {

        String email = request.getEmail();
        Optional<User> userOptional = userRepository.findByUsername(email);

        if (!userOptional.isPresent()) {
            throw new AccessDeniedException("Usuario no encontrado");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(expiration));
        recoverPassRepository.save(user);
        sendResetPasswordEmail(email, token);

    }

    public void resetPassword(RecoverPassRequestDto request) {

        String token = request.getToken();
        String newPassword = request.getNewPassword();

        Optional<User> userOptional = recoverPassRepository.findByResetToken(token);

        if (!userOptional.isPresent()) {
            throw new MalformedJwtException("Token invalido:");
        }

        User user = userOptional.get();

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new AccessDeniedException("El link expiro");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiration(null);
        recoverPassRepository.save(user);

    }

}

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
        message.setText("Para restablecer su contraseña, haga clic en el siguiente enlace: " +
                "http://localhost:5173/changeCode?token=" + token);
        javaMailSender.send(message);
    }

    public MessageDto forgotPassword(ForgotPassDto request) {

        String email = request.getEmail();
        Optional<User> userOptional = userRepository.findByUsername(email);

        if (!userOptional.isPresent()) {
            return new MessageDto("Usuario no encontrado");
        }

        User user = userOptional.get();
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setTokenExpiration(LocalDateTime.now().plusMinutes(expiration));
        recoverPassRepository.save(user);
        sendResetPasswordEmail(email, token);

        return new MessageDto("Correo electrónico de restablecimiento de contraseña enviado");
    }

    public MessageDto resetPassword(RecoverPassRequestDto request) {

        String token = request.getToken();
        String newPassword = request.getNewPassword();

        Optional<User> userOptional = recoverPassRepository.findByResetToken(token);

        if (!userOptional.isPresent()) {
            return new MessageDto("Token inválido");
        }

        User user = userOptional.get();

        if (user.getTokenExpiration().isBefore(LocalDateTime.now())) {
            return new MessageDto("Este link expiró");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiration(null);
        recoverPassRepository.save(user);

        return new MessageDto("Contraseña modificada con éxito");
    }

}

package com.consorcio.servicios.Security.Service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String mailSend;

    public void sendResetPasswordEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSend);
        message.setTo(email);
        message.setSubject("Cambio de contraseña");
        message.setText("Para restablecer su contraseña, haga clic en el siguiente enlace: " +
                    "http://localhost:3000/reset-password?token=" + token);
        javaMailSender.send(message);
    }

}

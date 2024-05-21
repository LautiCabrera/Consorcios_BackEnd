package com.consorcio.servicios.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String mailSend;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSend);
        message.setTo("example@gmail.com");
        message.setSubject("Prueba envío email simple");
        message.setText("Esto es el contenido el email");
        javaMailSender.send(message);
    }

}

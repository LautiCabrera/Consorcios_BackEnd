package com.consorcio.servicios.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Enums.Role;
import jakarta.transaction.Transactional;

@Component
public class UserAdminSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Verificar si ya existe un usuario admin
        if (userRepository.count() == 0) {
            // Crear el usuario admin
            User adminUser = new User();
            adminUser.setDni(12345678);
            adminUser.setFirstName("Lucra");
            adminUser.setLastName("Company");
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setPhone("123456789");
            adminUser.setRole(Role.ROLE_ADMIN);
            adminUser.setStatus(UserStatus.ACTIVE);
            adminUser.setResetToken(null);
            adminUser.setTokenExpiration(null);

            // Guardar el usuario admin en la base de datos
            userRepository.save(adminUser);

            System.out.println("Usuario administrador creado exitosamente.");
        } else {
            System.out.println("El usuario administrador ya existe.");
        }
    }

}
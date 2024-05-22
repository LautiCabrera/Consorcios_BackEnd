package com.consorcio.servicios.Security.Service;

import com.consorcio.servicios.Security.Entity.AuthResponse;
import com.consorcio.servicios.Security.Entity.RegisterRequest;
import com.consorcio.servicios.Security.Entity.LoginRequest;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;

        public AuthResponse login(LoginRequest request) {

                User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

                String token = jwtService.getToken(user);

                return new AuthResponse(token);
        }

        public AuthResponse userRegister(RegisterRequest request) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .phone(request.getPhone())
                                .dni(request.getDni())
                                .role(Role.ROLE_USER)
                                .build();
                userRepository.save(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();
        }

        public AuthResponse adminRegister(RegisterRequest request) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .phone(request.getPhone())
                                .dni(request.getDni())
                                .role(Role.ROLE_ADMIN)
                                .build();
                userRepository.save(user);

                return AuthResponse.builder()
                                .token(jwtService.getToken(user))
                                .build();
        }

}
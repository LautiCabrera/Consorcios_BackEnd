package com.consorcio.servicios.Security.Service;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Dto.AuthResponseDto;
import com.consorcio.servicios.Security.Dto.LoginRequestDto;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        public AuthResponseDto login(LoginRequestDto request) {

                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

                String token = jwtService.getToken(user);

                return new AuthResponseDto(token);
        }

        public AuthResponseDto register(@Valid RegisterRequestDto request, Role role) {
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .phone(request.getPhone())
                                .dni(request.getDni())
                                .role(role)
                                .build();
                userRepository.save(user);

                return AuthResponseDto.builder()
                                .token(jwtService.getToken(user))
                                .build();
        }

}
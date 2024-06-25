package com.consorcio.servicios.Security.Service;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Dto.AuthResponseDto;
import com.consorcio.servicios.Security.Dto.LoginRequestDto;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
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

        public void register(@Valid RegisterRequestDto request, Role role) {
            
                Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
                
                if (existingUser.isPresent()) {
                    throw new IllegalArgumentException("El nombre de usuario ya está en uso");
                }
            
                User user = User.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .firstname(request.getFirstname())
                                .lastname(request.getLastname())
                                .phone(request.getPhone())
                                .dni(request.getDni())
                                .role(role)
                                .status(UserStatus.ACTIVE)
                                .build();
                userRepository.save(user);
                
                try {
                    userRepository.save(user);
                } catch (DataIntegrityViolationException e) {
                    throw new IllegalArgumentException("Error de integridad de datos: " + e.getMessage(), e);
                } catch (AccessDeniedException e) {
                    throw new AccessDeniedException("Access denied: " + e.getMessage(), e);
                }

        }

}
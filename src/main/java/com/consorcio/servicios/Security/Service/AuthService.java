package com.consorcio.servicios.Security.Service;

import com.consorcio.servicios.Security.Entity.AuthResponse;
import com.consorcio.servicios.Security.Entity.RegisterRequest;
import com.consorcio.servicios.Security.Entity.LoginRequest;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Dto.JwtDto;
import com.consorcio.servicios.Security.Enums.Role;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        public JwtDto login(LoginRequest request) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

                User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

                String token = jwtService.getToken(user);

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                List<String> authorities = userDetails.getAuthorities().stream()
                                .map(authority -> authority.getAuthority())
                                .collect(Collectors.toList());

                boolean isLogin = authentication.isAuthenticated();

                return new JwtDto(token, authorities, isLogin);
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
package com.consorcio.servicios.Security.Auth;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Dto.JwtDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Jwt.JwtService;
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

    public JwtDto login(LoginRequest request){
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        
        String token = jwtService.getToken(user);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Crear un objeto AuthResponse con el token
        AuthResponse builder = AuthResponse.builder()
                .token(token)
                .build();

        // Crear un JwtDto con el token y las autoridades
        JwtDto jwt = new JwtDto(builder.getToken(), userDetails.getAuthorities());

        return jwt;
    }
    
    public AuthResponse userRegister(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
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
    
}
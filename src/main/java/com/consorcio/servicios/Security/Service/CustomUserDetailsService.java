package com.consorcio.servicios.Security.Service;

import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Security.Enums.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository usersRepository;

    public CustomUserDetailsService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Carga el usuario desde la base de datos
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Obtiene el rol del usuario usando tu lógica de servicio
        Role role = usersRepository.findRoleForUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Role not found for user"));

        // Devuelve un CustomUserDetails con el usuario y su rol
        return new CustomUserDetails(user, role);
    }

}

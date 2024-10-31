package com.consorcio.servicios.Entity;

import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Enums.Role;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false)
    private int dni;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column(nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    UserStatus status;

    private String resetToken;

    private LocalDateTime tokenExpiration;

    @Column(name = "id_user_update")
    private Long idUserUpdate;

    @Column(name = "id_user_register")
    private Long idUserRegister;

    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_register")
    private LocalDateTime datRegister;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
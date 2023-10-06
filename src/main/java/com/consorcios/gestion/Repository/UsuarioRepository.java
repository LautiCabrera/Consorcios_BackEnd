package com.consorcios.gestion.Repository;

import com.consorcios.gestion.Entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Optional<Usuario> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}

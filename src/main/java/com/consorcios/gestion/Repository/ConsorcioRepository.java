package com.consorcios.gestion.Repository;

import java.util.Optional;
import com.consorcios.gestion.Entity.Consorcio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsorcioRepository extends JpaRepository<Consorcio, Integer> {
    public Optional<Consorcio> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
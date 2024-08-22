package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Integer> {
    
    //Metodos de consultas personalizadas
    
}

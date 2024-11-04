package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    
    //Obtiene la lectura actual
    @Query("SELECT r FROM Reading r WHERE r.idMeter = :idMeter AND r.idPeriod = :idPeriod ORDER BY r.dateReading DESC LIMIT 1")
    Reading findCurrentReadingByMeterAndPeriod(Long idMeter, Long idPeriod);
    
    //Obtiene la lectura anterior
    @Query("SELECT r FROM Reading r WHERE r.idMeter = :idMeter AND r.dateReading < (SELECT MAX(rr.dateReading) FROM Reading rr WHERE rr.idMeter = :idMeter AND rr.idPeriod = :idPeriod) ORDER BY r.dateReading DESC LIMIT 1")
    Reading findPreviousReadingByMeter(Long idMeter, Long idPeriod);
}
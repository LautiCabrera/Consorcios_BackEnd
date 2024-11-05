package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.Read.ReadReadingDto;
import com.consorcio.servicios.Entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    // Obtiene la lectura actual
    @Query("SELECT r FROM Reading r WHERE r.idMeter = :idMeter AND r.idPeriod = :idPeriod ORDER BY r.dateReading DESC LIMIT 1")
    Reading findCurrentReadingByMeterAndPeriod(Long idMeter, Long idPeriod);

    // Obtiene la lectura anterior
    @Query("SELECT r FROM Reading r WHERE r.idMeter = :idMeter AND r.dateReading < (SELECT MAX(rr.dateReading) FROM Reading rr WHERE rr.idMeter = :idMeter AND rr.idPeriod = :idPeriod) ORDER BY r.dateReading DESC LIMIT 1")
    Reading findPreviousReadingByMeter(Long idMeter, Long idPeriod);

    @Query("SELECT NEW com.consorcio.servicios.Dto.Read.ReadReadingDto(r.idReading, r.reading, p.name, DATE(r.dateRegister)) "
            +
            "FROM Reading r " +
            "JOIN Meter m ON r.idMeter = m.idMeter " +
            "JOIN Residence res ON m.idMeter = res.idMeter " +
            "JOIN Period p ON r.idPeriod = p.idPeriod")
    List<ReadReadingDto> findAllReadings();

    @Query("SELECT NEW com.consorcio.servicios.Dto.Read.ReadReadingDto(r.idReading, r.reading, p.name, DATE(r.dateRegister)) "
            +
            "FROM Reading r " +
            "JOIN Meter m ON r.idMeter = m.idMeter " +
            "JOIN Residence res ON m.idMeter = res.idMeter " +
            "JOIN Period p ON r.idPeriod = p.idPeriod " +
            "WHERE res.idUser = :idUser")
    List<ReadReadingDto> findReadingsByUserId(@Param("idUser") Long idUser);

}
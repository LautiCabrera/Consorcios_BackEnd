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

    @Query("SELECT NEW com.consorcio.servicios.Dto.Read.ReadReadingDto(r.idReading, r.reading, p.name, DATE(r.dateRegister)) " +
            "FROM Reading r " +
            "JOIN Meter m ON r.idMeter = m.idMeter " +
            "JOIN Residence res ON m.idMeter = res.idMeter " +
            "JOIN Period p ON r.idPeriod = p.idPeriod")
    List<ReadReadingDto> findAllReadings();

    @Query("SELECT NEW com.consorcio.servicios.Dto.Read.ReadReadingDto(r.idReading, r.reading, p.name, DATE(r.dateRegister)) " +
            "FROM Reading r " +
            "JOIN Meter m ON r.idMeter = m.idMeter " +
            "JOIN Residence res ON m.idMeter = res.idMeter " +
            "JOIN Period p ON r.idPeriod = p.idPeriod " +
            "WHERE res.idUser = :idUser")
    List<ReadReadingDto> findReadingsByUserId(@Param("idUser") Long idUser);

}
package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    public Meter findByIdMeter(Long idMeter);

    @Query("SELECT m FROM Meter m WHERE m.idMeter = (SELECT res.idMeter FROM Residence res WHERE res.idUser = :idUser)")
    Meter findMeterByUserId(@Param("idUser") Long idUser);
}

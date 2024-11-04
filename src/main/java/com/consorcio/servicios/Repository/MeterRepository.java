package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    public Meter findByIdMeter(Long idMeter);

}
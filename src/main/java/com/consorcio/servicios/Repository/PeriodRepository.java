package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.Read.ReadPeriodDto;
import com.consorcio.servicios.Entity.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    @Query("SELECT new com.consorcio.servicios.Dto.Read.ReadPeriodDto(p.idPeriod, p.name, p.idModality) " +
            "FROM Period p WHERE p.idModality = :idModality")
    List<ReadPeriodDto> findPeriodsByModalityId(@Param("idModality") Long idModality);

    @Query("SELECT new com.consorcio.servicios.Dto.Read.ReadPeriodDto(p.idPeriod, p.name, p.idModality) " +
            "FROM Period p JOIN Modality m ON p.idModality = m.idModality " +
            "WHERE m.active = true")
    List<ReadPeriodDto> findPeriodsByActiveModality();

}
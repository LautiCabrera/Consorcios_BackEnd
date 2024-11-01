package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {

    @Query("SELECT r.idResidence FROM Residence r WHERE r.idUser = :idUser")
    Optional<Long> findIdResidenceByUserId(@Param("idUser") Long idUser);

    @Query("SELECT r.idMeter FROM Residence r WHERE r.idResidence = :idResidence")
    Optional<Long> findIdMeterByResidenceId(@Param("idResidence") Long idResidence);

}
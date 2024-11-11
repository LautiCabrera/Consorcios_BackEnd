package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.Read.ReadResidenceDto;
import com.consorcio.servicios.Entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, Long> {

    @Query("SELECT r.idResidence FROM Residence r WHERE r.idUser = :idUser")
    Optional<Long> findIdResidenceByUserId(@Param("idUser") Long idUser);

    @Query("SELECT r.idMeter FROM Residence r WHERE r.idResidence = :idResidence")
    Optional<Long> findIdMeterByResidenceId(@Param("idResidence") Long idResidence);

    @Query("SELECT NEW com.consorcio.servicios.Dto.Read.ReadResidenceDto(u.idUser, r.idResidence, r.district, r.street, r.number, l.idLocation, m.numberMeter, f.idFee) "
            + "FROM Residence r "
            + "JOIN User u ON r.idUser = u.idUser "
            + "JOIN Location l ON r.idLocation = l.idLocation "
            + "JOIN Meter m ON r.idMeter = m.idMeter "
            + "JOIN Fee f ON m.idFee = f.idFee ")
    List<ReadResidenceDto> findAllResidences();

}
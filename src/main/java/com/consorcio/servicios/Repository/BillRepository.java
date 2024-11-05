package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.Read.BillDto;
import com.consorcio.servicios.Dto.Read.PaymentDto;
import com.consorcio.servicios.Entity.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    //Buscar factura por usuario y periodo
    @Query("SELECT new com.consorcio.servicios.Dto.Read.BillDto("
            + "u.firstName, u.lastName, r.street, u.username, l.name, "
            + "prov.name, country.name, DATE(b.dateRegister), b.idBill, b.normalConsumption, "
            + "b.socialQuota, b.surplus, b.interests, b.fines, b.reconnection, "
            + "b.connection, b.materials, b.others, b.discount, b.total) "
            + "FROM Bill b "
            + "JOIN Meter m ON b.idMeter = m.idMeter "
            + "JOIN Residence r ON m.idMeter = r.idMeter "
            + "JOIN User u ON r.idUser = u.idUser "
            + "JOIN Location l ON r.idLocation = l.idLocation "
            + "JOIN Province prov ON l.idProvince = prov.idProvince "
            + "JOIN Country country ON prov.idCountry = country.idCountry "
            + "JOIN Reading reading ON reading.idMeter = m.idMeter AND reading.idPeriod = :idPeriod "
            + "WHERE u.idUser = :idUser")
    BillDto findBillDetailsByUserAndPeriod(@Param("idUser") Long idUser, @Param("idPeriod") Long idPeriod);

    @Query("SELECT new com.consorcio.servicios.Dto.Read.PaymentDto(b.idBill, DATE(b.dateRegister), b.paidStatus, b.total) " +
            "FROM Bill b " +
            "JOIN Meter m ON b.idMeter = m.idMeter " +
            "JOIN Residence r ON m.idMeter = r.idMeter " +
            "JOIN User u ON r.idUser = u.idUser " +
            "WHERE u.idUser = :idUser")
    List<PaymentDto> findPaymentsByUserId(@Param("idUser") Long idUser);

    @Modifying
    @Transactional
    @Query("UPDATE Bill b SET b.paidStatus = NOT b.paidStatus WHERE b.idBill = :idBill")
    void togglePaidStatusById(@Param("idBill") Long idBill);

}
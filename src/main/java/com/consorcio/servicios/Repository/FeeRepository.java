package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Dto.FeeDto;
import com.consorcio.servicios.Entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    public Fee findByIdFee(Long idFee);

    @Query("SELECT new com.consorcio.servicios.Dto.FeeDto(f.idFee, f.name, f.price, f.consumptionMax) FROM Fee f")
    List<FeeDto> findAllFees();

}

package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    public Fee findByIdFee(Long idFee);

}

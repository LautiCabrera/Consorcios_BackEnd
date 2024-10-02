package com.consorcio.servicios.Repository;

import com.consorcio.servicios.Entity.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalityRepository extends JpaRepository<Modality, Long> {

}
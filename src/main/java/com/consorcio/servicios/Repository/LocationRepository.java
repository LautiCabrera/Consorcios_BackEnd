package com.consorcio.servicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.consorcio.servicios.Entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
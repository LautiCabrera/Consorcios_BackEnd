package com.consorcio.servicios.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.consorcio.servicios.Entity.Location;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.idProvince = :idMendoza")
    List<Location> findLocationsByProvinceId(@Param("idMendoza") Long idMendoza);

}
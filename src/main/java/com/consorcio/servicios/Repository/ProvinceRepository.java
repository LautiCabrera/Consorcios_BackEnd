package com.consorcio.servicios.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.consorcio.servicios.Entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    List<Province> findByIdCountry(Long idCountry);

    Province findByName(String name);

}
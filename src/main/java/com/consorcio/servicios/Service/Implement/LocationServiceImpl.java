package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Entity.Location;
import com.consorcio.servicios.Repository.LocationRepository;
import com.consorcio.servicios.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getLocationsByProvince(Long idProvince) {
        return locationRepository.findLocationsByProvinceId(idProvince);
    }

}
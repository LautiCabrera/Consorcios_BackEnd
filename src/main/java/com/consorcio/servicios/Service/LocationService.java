package com.consorcio.servicios.Service;

import com.consorcio.servicios.Entity.Location;
import java.util.List;

public interface LocationService {

    public List<Location> getLocationsByProvince(Long idProvince);

}
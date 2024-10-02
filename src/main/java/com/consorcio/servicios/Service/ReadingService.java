package com.consorcio.servicios.Service;

import com.consorcio.servicios.Entity.Reading;
import java.util.List;

public interface ReadingService {

    public List<Reading> getAllReadings();

    public void createReading(Reading reading);

    public void updateReading(Long idReading, Reading reading);

}
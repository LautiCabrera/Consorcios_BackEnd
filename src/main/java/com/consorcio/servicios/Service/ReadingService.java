package com.consorcio.servicios.Service;

import com.consorcio.servicios.Entity.Reading;
import java.util.List;

public interface ReadingService {

    public List<Reading> getAllReadings();

    public Reading getReadingById(int id);

    public Reading createReading(Reading reading);

    public Reading updateReading(Reading reading);

}
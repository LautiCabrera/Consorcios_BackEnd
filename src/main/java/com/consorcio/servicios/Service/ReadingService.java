package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.Create.CreateReadingDto;
import com.consorcio.servicios.Dto.Update.UpdateReadingDto;
import com.consorcio.servicios.Entity.Reading;
import java.util.List;

public interface ReadingService {

    public List<Reading> getAllReadings();

    public void createReading(CreateReadingDto reading);

    public void updateReading(Long idReading, UpdateReadingDto reading);

}
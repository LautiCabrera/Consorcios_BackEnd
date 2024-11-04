package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.Read.ReadReadingDto;
import com.consorcio.servicios.Dto.ReadingDto;
import java.util.List;

public interface ReadingService {

    public List<ReadReadingDto> getAllReadings();

    public List<ReadReadingDto> getReadingsByUserId(long idUser);

    public void createReading(Long idUser, ReadingDto reading);

    public void updateReading(Long idReading, ReadingDto reading);

}
package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    @Override
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public void createReading(Reading reading) {
        readingRepository.save(reading);
    }

    @Override
    public void updateReading(Long idReading, Reading readingDto) {

        Reading reading = readingRepository.findById(idReading)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado para actualización"));
        reading.setReading(readingDto.getReading());

        readingRepository.save(reading);
    }

}

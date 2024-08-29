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
    public Reading getReadingById(int id) {
        return readingRepository.findById(id).orElse(null);
    }

    @Override
    public Reading createReading(Reading reading) {
        return readingRepository.save(reading);
    }

    @Override
    public Reading updateReading(Reading reading) {
        return readingRepository.save(reading);
    }

}

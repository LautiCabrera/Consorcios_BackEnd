package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Create.CreateReadingDto;
import com.consorcio.servicios.Dto.Update.UpdateReadingDto;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Security.Config.Authenticated;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    @Override
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public void createReading(CreateReadingDto reading) {
        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        Reading readingEntity = Reading.builder()
                .reading(reading.getReading())
                .idMeter(reading.getIdMeter())
                .idPeriod(reading.getIdPeriod())
                .dateReading(LocalDateTime.now())
                .idUserRegister(currentUser.getUser().getIdUserRegister())
                .idUserUpdate(currentUser.getUser().getIdUserRegister())
                .build();

        readingRepository.save(readingEntity);
    }

    @Override
    public void updateReading(Long idReading, UpdateReadingDto readingDto) {

        Reading reading = readingRepository.findById(idReading)
                .orElseThrow(() -> new RuntimeException("Lectura no encontrada para actualización"));

        reading.setReading(readingDto.getReading());
        reading.setIdMeter(readingDto.getIdMeter());
        reading.setIdPeriod(readingDto.getIdPeriod());
        reading.setDateUpdate(LocalDateTime.now());

        readingRepository.save(reading);
    }

}

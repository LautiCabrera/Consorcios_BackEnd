package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Create.CreateReadingDto;
import com.consorcio.servicios.Dto.Update.UpdateReadingDto;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Repository.ResidenceRepository;
import com.consorcio.servicios.Security.Config.Authenticated;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private ResidenceRepository residenceRepository;

    @Override
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public void createReading(Long idUser, CreateReadingDto reading) {

        Long idResidence = residenceRepository.findIdResidenceByUserId(idUser)
                .orElseThrow(() -> new RuntimeException("Residencia no encotrada para el usuario"));

        Long idMeter = residenceRepository.findIdMeterByResidenceId(idResidence)
                .orElseThrow(() -> new RuntimeException("Medidor no encontrado para la residencia"));

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        Reading readingEntity = Reading.builder()
                .reading(reading.getReading())
                .idMeter(idMeter)
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
        reading.setIdPeriod(readingDto.getIdPeriod());
        reading.setDateUpdate(LocalDateTime.now());

        readingRepository.save(reading);
    }

}

package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Read.ReadReadingDto;
import com.consorcio.servicios.Dto.ReadingDto;
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
    public List<ReadReadingDto> getAllReadings() {
        return readingRepository.findAllReadings();
    }

    @Override
    public List<ReadReadingDto> getReadingsByUserId(long idUser) {
        return readingRepository.findReadingsByUserId(idUser);
    }

    @Override
    public void createReading(Long idUser, ReadingDto reading) {

        Long idResidence = residenceRepository.findIdResidenceByUserId(idUser)
                .orElseThrow(() -> new RuntimeException("Residencia no encotrada para el usuario"));

        Long idMeter = residenceRepository.findIdMeterByResidenceId(idResidence)
                .orElseThrow(() -> new RuntimeException("Medidor no encontrado para la residencia"));

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        Reading readingEntity = Reading.builder()
                .reading(reading.getReading())
                .idMeter(idMeter)
                .idPeriod(reading.getIdPeriod())
                .dateRegister(LocalDateTime.now())
                .dateUpdate(LocalDateTime.now())
                .build();

        readingEntity.setIdUserRegister(currentUser.getUser().getIdUser());
        readingEntity.setIdUserUpdate(currentUser.getUser().getIdUser());

        readingRepository.save(readingEntity);
    }

    @Override
    public void updateReading(Long idReading, ReadingDto readingDto) {

        Reading reading = readingRepository.findById(idReading)
                .orElseThrow(() -> new RuntimeException("Lectura no encontrada para actualización"));

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        reading.setReading(readingDto.getReading());
        reading.setIdPeriod(readingDto.getIdPeriod());
        reading.setIdUserUpdate(currentUser.getUser().getIdUser());
        reading.setDateUpdate(LocalDateTime.now());

        readingRepository.save(reading);
    }

}
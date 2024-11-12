package com.consorcio.servicios.test;

import com.consorcio.servicios.Dto.Read.ReadReadingDto;
import com.consorcio.servicios.Dto.ReadingDto;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Repository.ResidenceRepository;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Service.Implement.ReadingServiceImpl;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReadingServiceImplTest {

    @Mock
    private ReadingRepository readingRepository;

    @Mock
    private ResidenceRepository residenceRepository;

    @InjectMocks
    private ReadingServiceImpl readingService;

    @Mock
    private CustomUserDetails customUserDetails;

    @Test
    void testGetAllReadings() {
        // Simular datos de lectura
        List<ReadReadingDto> readings = Arrays.asList(
                new ReadReadingDto(1L, 1L, 100.0, "2023-Q4", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())),
                new ReadReadingDto(2L, 1L, 200.0, "2023-Q4", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
        );
        when(readingRepository.findAllReadings()).thenReturn(readings);

        List<ReadReadingDto> result = readingService.getAllReadings();
        assertEquals(2, result.size());
        verify(readingRepository, times(1)).findAllReadings();
    }

    @Test
    void testGetReadingsByUserId() {
        long userId = 1L;
        List<ReadReadingDto> readings = Arrays.asList(
                new ReadReadingDto(1L, userId, 100.0, "2023-Q4", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
        );
        when(readingRepository.findReadingsByUserId(userId)).thenReturn(readings);

        List<ReadReadingDto> result = readingService.getReadingsByUserId(userId);
        assertEquals(1, result.size());
        verify(readingRepository, times(1)).findReadingsByUserId(userId);
    }

    @Test
    void testCreateReading() {
        Long userId = 1L;
        ReadingDto readingDto = new ReadingDto(100.1, 1L);
        Long residenceId = 1L;
        Long meterId = 1L;

        // Simulaciones
        when(residenceRepository.findIdResidenceByUserId(userId)).thenReturn(Optional.of(residenceId));
        when(residenceRepository.findIdMeterByResidenceId(residenceId)).thenReturn(Optional.of(meterId));
        when(customUserDetails.getUser()).thenReturn(mock(User.class));

        // Ejecutar el método
        readingService.createReading(userId, readingDto);

        // Verificar interacciones
        verify(residenceRepository, times(1)).findIdResidenceByUserId(userId);
        verify(residenceRepository, times(1)).findIdMeterByResidenceId(residenceId);
        verify(readingRepository, times(1)).save(any(Reading.class));
    }

    @Test
    void testUpdateReading() {
        Long readingId = 1L;
        ReadingDto readingDto = new ReadingDto(200.1, 2L);
        Reading reading = new Reading();
        reading.setIdReading(readingId);

        when(readingRepository.findById(readingId)).thenReturn(Optional.of(reading));
        when(customUserDetails.getUser()).thenReturn(mock(User.class));

        readingService.updateReading(readingId, readingDto);

        assertEquals(readingDto.getReading(), reading.getReading());
        assertEquals(readingDto.getIdPeriod(), reading.getIdPeriod());
        verify(readingRepository, times(1)).findById(readingId);
        verify(readingRepository, times(1)).save(reading);
    }

    @Test
    void testCreateReadingThrowsExceptionWhenResidenceNotFound() {
        Long userId = 1L;
        ReadingDto readingDto = new ReadingDto(100.1, 1L);

        when(residenceRepository.findIdResidenceByUserId(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> readingService.createReading(userId, readingDto));

        assertEquals("Residencia no encotrada para el usuario", exception.getMessage());
        verify(residenceRepository, times(1)).findIdResidenceByUserId(userId);
        verify(readingRepository, never()).save(any(Reading.class));
    }
}

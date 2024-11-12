package com.consorcio.servicios.test;

import com.consorcio.servicios.Dto.Read.ReadPeriodDto;
import com.consorcio.servicios.Entity.Period;
import com.consorcio.servicios.Repository.PeriodRepository;
import com.consorcio.servicios.Service.Implement.PeriodServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class PeriodServiceImplTest {

    @Mock
    private PeriodRepository periodRepository;

    @InjectMocks
    private PeriodServiceImpl periodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllModalities() {
        // Configuración de datos de prueba
        Period period1 = new Period(1L, "Periodo 1", 1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());
        Period period2 = new Period(2L, "Periodo 2", 2L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(periodRepository.findAll()).thenReturn(Arrays.asList(period1, period2));

        // Llamada al método de servicio
        List<Period> result = periodService.getAllModalities();

        // Verificaciones
        assertEquals(2, result.size());
        verify(periodRepository, times(1)).findAll();
    }

    @Test
    public void testGetPeriodByModalityId() {
        // Configuración de datos de prueba
        Long idModality = 1L;
        ReadPeriodDto periodDto1 = new ReadPeriodDto(1L, "Periodo 1", idModality);
        ReadPeriodDto periodDto2 = new ReadPeriodDto(2L, "Periodo 2", idModality);

        when(periodRepository.findPeriodsByModalityId(idModality)).thenReturn(Arrays.asList(periodDto1, periodDto2));

        // Llamada al método de servicio
        List<ReadPeriodDto> result = periodService.getPeriodByModalityId(idModality);

        // Verificaciones
        assertEquals(2, result.size());
        verify(periodRepository, times(1)).findPeriodsByModalityId(idModality);
    }

    @Test
    public void testGetPeriodsActives() {
        // Configuración de datos de prueba
        ReadPeriodDto activePeriod1 = new ReadPeriodDto(1L, "Periodo Activo 1", 1L);
        ReadPeriodDto activePeriod2 = new ReadPeriodDto(2L, "Periodo Activo 2", 2L);

        when(periodRepository.findPeriodsByActiveModality()).thenReturn(Arrays.asList(activePeriod1, activePeriod2));

        // Llamada al método de servicio
        List<ReadPeriodDto> result = periodService.getPeriodsActives();

        // Verificaciones
        assertEquals(2, result.size());
        verify(periodRepository, times(1)).findPeriodsByActiveModality();
    }

    @Test
    public void testCreatePeriod() {
        Period period = new Period(1L, "Nuevo Periodo", 1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        periodService.createPeriod(period);

        verify(periodRepository, times(1)).save(period);
    }

    @Test
    public void testUpdatePeriod() {
        Period period = new Period(1L, "Periodo Actualizado", 1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        periodService.updatePeriod(period);

        verify(periodRepository, times(1)).save(period);
    }

    @Test
    public void testDeletePeriod() {
        Long idPeriod = 1L;

        periodService.deletePeriod(idPeriod);

        verify(periodRepository, times(1)).deleteById(idPeriod);
    }
}

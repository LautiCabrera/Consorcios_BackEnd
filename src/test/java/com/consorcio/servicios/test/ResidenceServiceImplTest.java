package com.consorcio.servicios.test;

import com.consorcio.servicios.Dto.Read.ReadResidenceDto;
import com.consorcio.servicios.Dto.ResidenceDto;
import com.consorcio.servicios.Entity.Meter;
import com.consorcio.servicios.Entity.Residence;
import com.consorcio.servicios.Repository.MeterRepository;
import com.consorcio.servicios.Repository.ResidenceRepository;
import com.consorcio.servicios.Service.Implement.ResidenceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResidenceServiceImplTest {

    @Mock
    private ResidenceRepository residenceRepository;

    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private ResidenceServiceImpl residenceService;


    @Test
    void testGetAllResidences() {
        ReadResidenceDto dto1 = new ReadResidenceDto();
        ReadResidenceDto dto2 = new ReadResidenceDto();
        when(residenceRepository.findAllResidences()).thenReturn(Arrays.asList(dto1, dto2));

        List<ReadResidenceDto> residences = residenceService.getAllResidences();

        assertEquals(2, residences.size());
        verify(residenceRepository, times(1)).findAllResidences();
    }

    @Test
    void testCreateResidence_Success() {
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setIdUser(2L);
        residenceDto.setDistrict("District");
        residenceDto.setStreet("Street");
        residenceDto.setNumber(123L);
        residenceDto.setIdLocation(5L);
        residenceDto.setNumberMeter(12345L);
        residenceDto.setIdFee(1L);

        when(residenceRepository.findIdResidenceByUserId(residenceDto.getIdUser())).thenReturn(Optional.empty());

        residenceService.createResidence(residenceDto);

        verify(residenceRepository, times(1)).save(any(Residence.class));
        verify(meterRepository, times(1)).save(any(Meter.class));
    }

    @Test
    void testCreateResidence_UserAlreadyHasResidence() {
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setIdUser(2L);

        when(residenceRepository.findIdResidenceByUserId(residenceDto.getIdUser())).thenReturn(Optional.of(1L));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> residenceService.createResidence(residenceDto));
        assertEquals("El usuario ya posee una residencia", exception.getMessage());
    }

    @Test
    void testCreateResidence_MissingMeterOrFeeData() {
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setIdUser(2L);
        residenceDto.setNumberMeter(12345L);
        residenceDto.setIdFee(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> residenceService.createResidence(residenceDto));
        assertEquals("Se deben proporcionar todos los datos del medidor", exception.getMessage());
    }

    @Test
    void testUpdateResidence_SuccessWithExistingMeter() {
        Long idResidence = 1L;
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setDistrict("Updated District");
        residenceDto.setStreet("Updated Street");
        residenceDto.setNumber(321L);
        residenceDto.setIdLocation(10L);
        residenceDto.setNumberMeter(98765L);
        residenceDto.setIdFee(2L);

        Residence residence = new Residence();
        residence.setIdResidence(idResidence);
        residence.setIdMeter(1L);

        Meter existingMeter = new Meter();
        existingMeter.setIdMeter(1L);

        when(residenceRepository.findById(idResidence)).thenReturn(Optional.of(residence));
        when(meterRepository.findById(residence.getIdMeter())).thenReturn(Optional.of(existingMeter));

        residenceService.updateResidence(idResidence, residenceDto);

        verify(residenceRepository, times(1)).save(residence);
        verify(meterRepository, times(1)).save(existingMeter);
        assertEquals("Updated District", residence.getDistrict());
    }

    @Test
    void testUpdateResidence_ResidenceNotFound() {
        Long idResidence = 1L;
        ResidenceDto residenceDto = new ResidenceDto();

        when(residenceRepository.findById(idResidence)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> residenceService.updateResidence(idResidence, residenceDto));
        assertEquals("Residencia no encontrada para el usuario", exception.getMessage());
    }

    @Test
    void testUpdateResidence_MeterNotFound() {
        Long idResidence = 1L;
        ResidenceDto residenceDto = new ResidenceDto();
        residenceDto.setNumberMeter(98765L);
        residenceDto.setIdFee(2L);

        Residence residence = new Residence();
        residence.setIdResidence(idResidence);
        residence.setIdMeter(1L);

        when(residenceRepository.findById(idResidence)).thenReturn(Optional.of(residence));
        when(meterRepository.findById(residence.getIdMeter())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> residenceService.updateResidence(idResidence, residenceDto));
        assertEquals("Medidor no encontrado para la residencia", exception.getMessage());
    }
}
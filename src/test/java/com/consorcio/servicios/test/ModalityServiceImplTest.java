package com.consorcio.servicios.test;

import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Repository.ModalityRepository;
import com.consorcio.servicios.Service.Implement.ModalityServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class ModalityServiceImplTest {
    
@Mock
    private ModalityRepository modalityRepository;

    @InjectMocks
    private ModalityServiceImpl modalityService;

    private Modality modality;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Creación de un objeto Modality para usar en las pruebas
        modality = new Modality(1L, "Modality 1", true, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void testGetAllModalities() {
        // Configuración de los datos de prueba
        Modality modality1 = new Modality(1L, "Modality 1", true, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());
        Modality modality2 = new Modality(2L, "Modality 2", false, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        when(modalityRepository.findAll()).thenReturn(Arrays.asList(modality1, modality2));

        // Llamada al método
        var result = modalityService.getAllModalities();

        // Verificaciones
        assertEquals(2, result.size());
        assertEquals("Modality 1", result.get(0).getName());
        verify(modalityRepository, times(1)).findAll();
    }

    @Test
    public void testGetModalityByIdFound() {
        // Configuración de los datos de prueba
        when(modalityRepository.findById(1L)).thenReturn(Optional.of(modality));

        // Llamada al método
        Modality result = modalityService.getModalityById(1L);

        // Verificaciones
        assertNotNull(result);
        assertEquals("Modality 1", result.getName());
        verify(modalityRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetModalityByIdNotFound() {
        // Configuración de los datos de prueba
        when(modalityRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamada al método
        Modality result = modalityService.getModalityById(1L);

        // Verificaciones
        assertNull(result);
        verify(modalityRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateModality() {
        // Configuración de los datos de prueba
        Modality newModality = new Modality(null, "New Modality", false, 1L, 1L, LocalDateTime.now(), LocalDateTime.now());

        // Llamada al método
        modalityService.createModality(newModality);

        // Verificación
        verify(modalityRepository, times(1)).save(newModality);
    }

    @Test
    public void testUpdateModality() {
        // Llamada al método
        modalityService.updateModality(modality);

        // Verificación
        verify(modalityRepository, times(1)).save(modality);
    }

    @Test
    public void testDeleteModality() {
        // Configuración de los datos de prueba
        when(modalityRepository.findById(1L)).thenReturn(Optional.of(modality));

        // Llamada al método
        modalityService.deleteModality(1L);

        // Verificación
        assertFalse(modality.isActive());  // Verifica que el estado de "active" cambie a false
        verify(modalityRepository, times(1)).save(modality);
    }

    @Test
    public void testDeleteModalityNotFound() {
        // Configuración de los datos de prueba
        when(modalityRepository.findById(1L)).thenReturn(Optional.empty());

        // Llamada al método
        modalityService.deleteModality(1L);

        // Verificación
        verify(modalityRepository, times(0)).save(any(Modality.class)); // No se debe guardar
    }
}

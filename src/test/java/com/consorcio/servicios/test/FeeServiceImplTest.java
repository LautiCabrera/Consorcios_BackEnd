package com.consorcio.servicios.test;

import com.consorcio.servicios.Dto.FeeDto;
import com.consorcio.servicios.Repository.FeeRepository;
import com.consorcio.servicios.Service.Implement.FeeServiceImpl;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class FeeServiceImplTest {

    @Mock
    private FeeRepository feeRepository;

    @InjectMocks
    private FeeServiceImpl feeService;

    private FeeDto feeDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de FeeDto para las pruebas
        feeDto = new FeeDto(1L, "Basic Fee", 100, 50);
    }

    @Test
    public void testGetAllFee() {
        // Configuración de los datos de prueba
        FeeDto fee1 = new FeeDto(1L, "Basic Fee", 100, 50);
        FeeDto fee2 = new FeeDto(2L, "Premium Fee", 200, 100);

        when(feeRepository.findAllFees()).thenReturn(Arrays.asList(fee1, fee2));

        // Llamada al método
        List<FeeDto> result = feeService.getAllFee();

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Basic Fee", result.get(0).getName());
        assertEquals("Premium Fee", result.get(1).getName());
        verify(feeRepository, times(1)).findAllFees();
    }

    @Test
    public void testGetAllFeeEmptyList() {
        // Configuración de los datos de prueba
        when(feeRepository.findAllFees()).thenReturn(Arrays.asList());

        // Llamada al método
        List<FeeDto> result = feeService.getAllFee();

        // Verificaciones
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(feeRepository, times(1)).findAllFees();
    }

    @Test
    public void testGetAllFeeNull() {
        // Configuración de los datos de prueba
        when(feeRepository.findAllFees()).thenReturn(null);

        // Llamada al método
        List<FeeDto> result = feeService.getAllFee();

        // Verificaciones
        assertNull(result);
        verify(feeRepository, times(1)).findAllFees();
    }
}

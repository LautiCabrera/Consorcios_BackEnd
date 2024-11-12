package com.consorcio.servicios.test;

import com.consorcio.servicios.Entity.Location;
import com.consorcio.servicios.Repository.LocationRepository;
import com.consorcio.servicios.Service.Implement.LocationServiceImpl;
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

public class LocationServiceImplTest {
    
 @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Location location;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Creación de un objeto Location para usar en las pruebas
        location = new Location(1L, "Location 1", 1L);
    }

    @Test
    public void testGetLocationsByProvince() {
        // Configuración de los datos de prueba
        Location location1 = new Location(1L, "Location 1", 1L);
        Location location2 = new Location(2L, "Location 2", 1L);

        when(locationRepository.findLocationsByProvinceId(1L)).thenReturn(Arrays.asList(location1, location2));

        // Llamada al método
        List<Location> result = locationService.getLocationsByProvince(1L);

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Location 1", result.get(0).getName());
        assertEquals("Location 2", result.get(1).getName());
        verify(locationRepository, times(1)).findLocationsByProvinceId(1L);
    }

    @Test
    public void testGetLocationsByProvinceNoLocations() {
        // Configuración de los datos de prueba
        when(locationRepository.findLocationsByProvinceId(1L)).thenReturn(Arrays.asList());

        // Llamada al método
        List<Location> result = locationService.getLocationsByProvince(1L);

        // Verificaciones
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(locationRepository, times(1)).findLocationsByProvinceId(1L);
    }

    @Test
    public void testGetLocationsByProvinceNull() {
        // Configuración de los datos de prueba
        when(locationRepository.findLocationsByProvinceId(1L)).thenReturn(null);

        // Llamada al método
        List<Location> result = locationService.getLocationsByProvince(1L);

        // Verificaciones
        assertNull(result);
        verify(locationRepository, times(1)).findLocationsByProvinceId(1L);
    }
}

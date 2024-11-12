package com.consorcio.servicios.test;

import com.consorcio.servicios.Entity.Bill;
import com.consorcio.servicios.Entity.Fee;
import com.consorcio.servicios.Entity.Meter;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Entity.User;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Repository.BillRepository;
import com.consorcio.servicios.Repository.FeeRepository;
import com.consorcio.servicios.Repository.MeterRepository;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Repository.UserRepository;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Service.Implement.BillServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class BillServiceImplTest {
    
@Mock
    private BillRepository billRepository;

    @Mock
    private ReadingRepository readingRepository;

    @Mock
    private MeterRepository meterRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FeeRepository feeRepository;

    @InjectMocks
    private BillServiceImpl billService;

    private Bill bill;
    private User user;
    private Meter meter;
    private Reading currentReading;
    private Reading previousReading;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de objetos de prueba
        user = new User();
        user.setIdUser(1L);

        meter = new Meter();
        meter.setIdMeter(1L);
        meter.setIdFee(1L);

        currentReading = new Reading();
        currentReading.setIdReading(1L);
        currentReading.setReading(100D);

        previousReading = new Reading();
        previousReading.setIdReading(2L);
        previousReading.setReading(80D);

        bill = new Bill();
        bill.setIdBill(1L);
        bill.setIdMeter(meter.getIdMeter());
        bill.setNormalConsumption(20.0);
        bill.setTotal(100.0);
        bill.setPaidStatus(false);
        bill.setDateRegister(LocalDateTime.now());
        bill.setDateUpdate(LocalDateTime.now());
    }

    @Test
    public void testGetAllBill() {
        when(billRepository.findAll()).thenReturn(Arrays.asList(bill));

        List<Bill> bills = billService.getAllBill();

        assertNotNull(bills);
        assertEquals(1, bills.size());
        assertEquals(bill.getIdBill(), bills.get(0).getIdBill());
        verify(billRepository, times(1)).findAll();
    }

    @Test
    public void testGetBillById() {
        when(billRepository.findById(1L)).thenReturn(Optional.of(bill));

        Bill result = billService.getBillById(1L);

        assertNotNull(result);
        assertEquals(bill.getIdBill(), result.getIdBill());
        verify(billRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBillByIdNotFound() {
        when(billRepository.findById(1L)).thenReturn(Optional.empty());

        Bill result = billService.getBillById(1L);

        assertNull(result);
        verify(billRepository, times(1)).findById(1L);
    }

    @Test
    public void testGenerateBill() {
        when(readingRepository.findCurrentReadingByMeterAndPeriod(1L, 1L)).thenReturn(currentReading);
        when(readingRepository.findPreviousReadingByMeter(1L, 1L)).thenReturn(previousReading);
        when(meterRepository.findMeterByUserId(1L)).thenReturn(meter);
        when(feeRepository.findByIdFee(1L)).thenReturn(new Fee(1L, "Tarifa Básica", 100, 50, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()));

        billService.generateBill(1L, 1L);

        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    public void testGenerateBillThrowsExceptionIfCurrentReadingIsNull() {
        when(readingRepository.findCurrentReadingByMeterAndPeriod(1L, 1L)).thenReturn(null);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            billService.generateBill(1L, 1L);
        });

        assertEquals("Lectura actual no encontrada para el usuario y período especificado.", thrown.getMessage());
    }

    @Test
    public void testGenerateBillForAllMeters() {
        User activeUser = new User();
        activeUser.setIdUser(1L);

        when(userRepository.findByRoleAndStatus(Role.ROLE_USER, UserStatus.ACTIVE)).thenReturn(Arrays.asList(activeUser));
        when(meterRepository.findMeterByUserId(1L)).thenReturn(meter);
        when(billRepository.existsByIdMeterAndIdPeriod(meter.getIdMeter(), 1L)).thenReturn(false);

        billService.generateBillForAllMeters(1L);
    }

    @Test
    public void testChangePaymentStatusByBillId() {
        doNothing().when(billRepository).togglePaidStatusById(1L);

        billService.changePaymentStatusByBillId(1L);

        verify(billRepository, times(1)).togglePaidStatusById(1L);
    }
}

package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Read.BillDto;
import com.consorcio.servicios.Dto.Read.PaymentDto;
import com.consorcio.servicios.Entity.*;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Repository.*;
import com.consorcio.servicios.Security.Config.Authenticated;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Service.BillService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private MeterRepository meterRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeeRepository feeRepository;

    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long Id) {
        return billRepository.findById(Id).orElse(null);
    }

    @Override
    public BillDto getBillByUserAndPeriod(Long IdUser, Long idPeriod) {
        return billRepository.findBillDetailsByUserAndPeriod(IdUser, idPeriod);
    }

    @Override
    public void generateBill(Long idUser, Long idPeriod) {
        // Obtener la lectura actual
        Reading currentReading = readingRepository.findCurrentReadingByMeterAndPeriod(idUser, idPeriod);

        if (currentReading == null) {
            throw new RuntimeException("Lectura actual no encontrada para el usuario y período especificado.");
        }

        // Obtener la lectura anterior, si existe
        Reading previousReading = readingRepository.findPreviousReadingByMeter(idUser, idPeriod);

        double consumption = 0; // Inicializamos el consumo

        // Si existe una lectura anterior, calculamos el consumo
        if (previousReading != null) {
            consumption = currentReading.getReading() - previousReading.getReading();
        }

        // Obtener el medidor (Meter) para el idUser dado
        Meter meter = meterRepository.findMeterByUserId(idUser);
        if (meter == null) {
            throw new RuntimeException("No se encontró un medidor asociado al usuario.");
        }

        // Obtener la tarifa (Fee) usando el idFee del medidor
        Fee fee = feeRepository.findByIdFee(meter.getIdFee());
        if (fee == null) {
            throw new RuntimeException("No se encontró una tarifa asociada al medidor.");
        }

        // Obtener datos de la tarifa
        double price = fee.getPrice();
        double consumptionMax = fee.getConsumptionMax();

        // Calcular total basado en el consumo y tarifa
        double normalConsumption = Math.min(consumption, consumptionMax);
        double surplus = Math.max(consumption - consumptionMax, 0);
        double total = price + (surplus > 0 ? calculateSurplusCharge(surplus) : 0);

        // Variable para completar otros campos de la factura
        long completar = 0;

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        // Crear la factura
        Bill bill = Bill.builder()
                .idMeter(meter.getIdMeter())
                .idReading(currentReading.getIdReading())
                .normalConsumption(normalConsumption)
                .surplus(surplus)
                .socialQuota(completar)
                .interests(completar)
                .fines(completar)
                .reconnection(completar)
                .connection(completar)
                .materials(completar)
                .others(completar)
                .discount(completar)
                .paidStatus(false)
                .total(total)
                .dateRegister(LocalDateTime.now())
                .dateUpdate(LocalDateTime.now())
                .build();

        bill.setIdUserRegister(currentUser.getUser().getIdUser());
        bill.setIdUserUpdate(currentUser.getUser().getIdUser());

        billRepository.save(bill);
    }

    @Override
    public void generateBillForAllMeters(Long idPeriod) {
        List<User> activeUsers = userRepository.findByRoleAndStatus(Role.ROLE_USER, UserStatus.ACTIVE);
        activeUsers.forEach(user -> generateBillForUser(user, idPeriod));
    }

    private void generateBillForUser(User user, Long idPeriod) {
        if (user != null) {
            Meter meter = meterRepository.findMeterByUserId(user.getIdUser());
            if (meter != null && !billRepository.existsByIdMeterAndIdPeriod(meter.getIdMeter(), idPeriod)) {
                generateBill(user.getIdUser(), idPeriod);
            }
        }
    }

    @Override
    public void sendBillToUser(Bill bill) {

    }

    @Override
    public List<PaymentDto> getPaymentsByUserId(Long idUser) {
        return billRepository.findPaymentsByUserId(idUser);
    }

    @Override
    public void changePaymentStatusByBillId(Long idBill) {
        billRepository.togglePaidStatusById(idBill);
    }

    private double calculateSurplusCharge(double surplus) {
        double extraChargePerUnit = 50.0;
        return surplus * extraChargePerUnit;
    }

}

package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Read.BillDto;
import com.consorcio.servicios.Entity.Bill;
import com.consorcio.servicios.Entity.Fee;
import com.consorcio.servicios.Entity.Meter;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Repository.BillRepository;
import com.consorcio.servicios.Repository.FeeRepository;
import com.consorcio.servicios.Repository.MeterRepository;
import com.consorcio.servicios.Repository.ReadingRepository;
import com.consorcio.servicios.Service.BillService;
import java.util.ArrayList;
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
    public Bill generateBill(Long idMeter, Long idPeriod) {
        // Obtener lectura actual y lectura anterior
        Reading currentReading = readingRepository.findCurrentReadingByMeterAndPeriod(idMeter, idPeriod);
        Reading previousReading = readingRepository.findPreviousReadingByMeter(idMeter, idPeriod);
        if (currentReading == null || previousReading == null) {
            throw new RuntimeException("Lectura actual o anterior no encontrada");
        }
        double consumption = currentReading.getReading() - previousReading.getReading();
        // Obtener el medidor (Meter) para el idMeter dado
        Meter meter = meterRepository.findByIdMeter(idMeter);
        // Obtener la tarifa (Fee) usando el idFee del medidor
        Fee fee = feeRepository.findByIdFee(meter.getIdFee());
        // Obtener datos de la tarifa
        double price = fee.getPrice();
        double consumptionMax = fee.getConsumptionMax();
        // Calcular total basado en el consumo y tarifa
        double normalConsumption = Math.min(consumption, consumptionMax);
        double surplus = Math.max(consumption - consumptionMax, 0);
        double total = price + (surplus > 0 ? calculateSurplusCharge(surplus) : 0);
        //Variable para completar los otros campos de la factura
        long completar = 98;
        // Crear la factura
        Bill bill = Bill.builder()
                .idMeter(idMeter)
                .idReading(currentReading.getIdReading())
                .normalConsumption(normalConsumption)
                .socialQuota(completar)
                .surplus(surplus)
                .interests(completar)
                .fines(completar)
                .reconnection(completar)
                .connection(completar)
                .materials(completar)
                .others(completar)
                .discount(completar)
                .total(total)
                .build();

        return billRepository.save(bill);
    }

    @Override
    public List<Bill> generateBillForAllMeters(Long idPeriod) {
        // Obtener todos los medidores
        List<Meter> meters = meterRepository.findAll();
        // Lista para almacenar las facturas generadas
        List<Bill> bills = new ArrayList<>();
        for (Meter meter : meters) {
            try {
                // Llamamos a generateBill para cada medidor y añadimos la factura generada a la lista
                Bill bill = generateBill(meter.getIdMeter(), idPeriod);
                bills.add(bill);
            } catch (Exception e) {
                // Manejo de errores si es necesario (por ejemplo, si faltan lecturas)
                System.out.println("Error al generar la factura para el medidor " + meter.getIdMeter() + ": " + e.getMessage());
            }
        }
        return bills;
    }

    @Override
    public void sendBillToUser(Bill bill) {

    }

    private double calculateSurplusCharge(double surplus) {
        double extraChargePerUnit = 50.0;
        return surplus * extraChargePerUnit;
    }
}

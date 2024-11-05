package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.Read.BillDto;
import com.consorcio.servicios.Dto.Read.PaymentDto;
import com.consorcio.servicios.Entity.Bill;
import java.util.List;

public interface BillService {

    public List<Bill> getAllBill();

    public Bill getBillById(Long Id);

    public BillDto getBillByUserAndPeriod(Long IdUser, Long idPeriod);

    public Bill generateBill(Long idMeter, Long idPeriod);

    public List<Bill> generateBillForAllMeters(Long idPeriod);

    public void sendBillToUser(Bill bill);

    public List<PaymentDto> getPaymentsByUserId(Long idUser);

}

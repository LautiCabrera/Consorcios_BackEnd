package com.consorcio.servicios.Service;

import com.consorcio.servicios.Entity.Bill;
import java.util.List;

public interface BillService {
    
    public List<Bill> getAllBill();
    public Bill getBillById (Long Id);
    public Bill generateBill (Long idMeter, Long idPeriod);
    public List<Bill> generateBillForAllMeters(Long idPeriod);
    public void sendBillToUser (Bill bill);
    
}

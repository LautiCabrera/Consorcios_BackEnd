package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.FeeDto;
import com.consorcio.servicios.Repository.FeeRepository;
import com.consorcio.servicios.Service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Override
    public List<FeeDto> getAllFee() {
        return feeRepository.findAllFees();
    }
}
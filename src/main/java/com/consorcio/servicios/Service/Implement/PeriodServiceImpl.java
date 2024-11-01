package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Read.ReadPeriodDto;
import com.consorcio.servicios.Entity.Period;
import com.consorcio.servicios.Repository.PeriodRepository;
import com.consorcio.servicios.Service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    public PeriodRepository periodRepository;

    @Override
    public List<Period> getAllModalities() {
        return periodRepository.findAll();
    }

    @Override
    public List<ReadPeriodDto> getPeriodByModalityId(Long idModality) {
        return periodRepository.findPeriodsByModalityId(idModality);
    }

    @Override
    public void createPeriod(Period period) {

    }

    @Override
    public void updatePeriod(Period period) {

    }

    @Override
    public void deletePeriod(Long idPeriod) {

    }

}
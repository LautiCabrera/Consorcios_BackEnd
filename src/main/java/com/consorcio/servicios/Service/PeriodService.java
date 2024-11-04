package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.Read.ReadPeriodDto;
import com.consorcio.servicios.Entity.Period;
import java.util.List;

public interface PeriodService {

    public List<Period> getAllModalities();

    public List<ReadPeriodDto> getPeriodByModalityId(Long idModality);

    public List<ReadPeriodDto> getPeriodsActives();

    public void createPeriod(Period period);

    public void updatePeriod(Period period);

    public void deletePeriod(Long idPeriod);

}
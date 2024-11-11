package com.consorcio.servicios.Service;

import com.consorcio.servicios.Dto.Read.ReadResidenceDto;
import com.consorcio.servicios.Dto.ResidenceDto;
import java.util.List;

public interface ResidenceService {

    public List<ReadResidenceDto> getAllResidences();

    public void createResidence(ResidenceDto residence);

    public void updateResidence(Long idResidence, ResidenceDto residence);

}
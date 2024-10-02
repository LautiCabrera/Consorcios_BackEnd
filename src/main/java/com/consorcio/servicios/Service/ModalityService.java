package com.consorcio.servicios.Service;

import com.consorcio.servicios.Entity.Modality;
import java.util.List;

public interface ModalityService {

    public List<Modality> getAllModalities();

    public Modality getModalityById(Long id);

    public Modality createModality(Modality modality);

    public Modality updateModality(Modality modality);

    public void deleteModality(Long id);

}
package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Repository.ModalityRepository;
import com.consorcio.servicios.Service.ModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ModalityServiceImpl implements ModalityService {

    @Autowired
    private ModalityRepository modalityRepository;

    @Override
    public List<Modality> getAllModalities() {
        return modalityRepository.findAll();
    }

    @Override
    public Modality getModalityById(Long id) {
        return modalityRepository.findById(id).orElse(null);
    }

    @Override
    public void createModality(Modality modality) {
        modality.setActive(false);
        modalityRepository.save(modality);
    }

    @Override
    public void updateModality(Modality modality) {
        modalityRepository.save(modality);
    }

    @Override
    public void deleteModality(Long id) {
        Modality modality = getModalityById(id);
        if (modality != null) {
            modality.setActive(false);
            modalityRepository.save(modality);
        }
    }

}
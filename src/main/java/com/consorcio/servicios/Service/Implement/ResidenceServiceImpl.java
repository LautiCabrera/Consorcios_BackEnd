package com.consorcio.servicios.Service.Implement;

import com.consorcio.servicios.Dto.Read.ReadResidenceDto;
import com.consorcio.servicios.Dto.ResidenceDto;
import com.consorcio.servicios.Entity.Meter;
import com.consorcio.servicios.Entity.Residence;
import com.consorcio.servicios.Repository.MeterRepository;
import com.consorcio.servicios.Repository.ResidenceRepository;
import com.consorcio.servicios.Security.Config.Authenticated;
import com.consorcio.servicios.Security.Config.CustomUserDetails;
import com.consorcio.servicios.Service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    private ResidenceRepository residenceRepository;
    @Autowired
    private MeterRepository meterRepository;

    @Override
    public List<ReadResidenceDto> getAllResidences() {
        return residenceRepository.findAllResidences();
    }

    @Override
    public void createResidence(ResidenceDto residenceDto) {

        if ((residenceDto.getNumberMeter() == null && residenceDto.getIdFee() != null) ||
                (residenceDto.getNumberMeter() != null && residenceDto.getIdFee() == null)) {
            throw new IllegalArgumentException("Se deben proporcionar todos los datos del medidor");
        }

        boolean exists = residenceRepository.findIdResidenceByUserId(residenceDto.getIdUser()).isPresent();
        if (exists) {
            throw new RuntimeException("El usuario ya posee una residencia");
        }

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        Residence residence = new Residence();
        residence.setIdUser(residenceDto.getIdUser());
        residence.setDistrict(residenceDto.getDistrict());
        residence.setStreet(residenceDto.getStreet());
        residence.setNumber(residenceDto.getNumber());
        residence.setIdLocation(residenceDto.getIdLocation());
        residence.setDateRegister(LocalDateTime.now());
        residence.setDateUpdate(LocalDateTime.now());
        residence.setIdUserRegister(currentUser.getUser().getIdUser());
        residence.setIdUserUpdate(currentUser.getUser().getIdUser());

        // Verificar si se proporcionó un número de medidor
        if (residenceDto.getNumberMeter() != null) {
            Meter meter = new Meter();

            meter.setNumberMeter(residenceDto.getNumberMeter());
            meter.setIdFee(residenceDto.getIdFee());
            meter.setDateRegister(LocalDateTime.now());
            meter.setDateUpdate(LocalDateTime.now());
            meter.setIdUserRegister(currentUser.getUser().getIdUser());
            meter.setIdUserUpdate(currentUser.getUser().getIdUser());

            // Guardar el medidor y asociar el ID a la residencia
            meterRepository.save(meter);
            residence.setIdMeter(meter.getIdMeter());
        }
        residenceRepository.save(residence);
    }

    @Override
    public void updateResidence(Long idResidence, ResidenceDto residenceDto) {

        if ((residenceDto.getNumberMeter() == null && residenceDto.getIdFee() != null) ||
                (residenceDto.getNumberMeter() != null && residenceDto.getIdFee() == null)) {
            throw new IllegalArgumentException("Se deben proporcionar todos los datos del medidor");
        }

        Residence residence = residenceRepository.findById(idResidence)
                .orElseThrow(() -> new RuntimeException("Residencia no encontrada para el usuario"));

        CustomUserDetails currentUser = Authenticated.getAuthenticatedUser();

        residence.setDistrict(residenceDto.getDistrict());
        residence.setStreet(residenceDto.getStreet());
        residence.setNumber(residenceDto.getNumber());
        residence.setIdLocation(residenceDto.getIdLocation());
        residence.setDateUpdate(LocalDateTime.now());
        residence.setIdUserUpdate(currentUser.getUser().getIdUser());

        // Verificar si se proporcionó un número de medidor
        if (residenceDto.getNumberMeter() != null) {
            Meter meter;
            if (residence.getIdMeter() != null) {
                // Actualizar el medidor existente
                meter = meterRepository.findById(residence.getIdMeter())
                        .orElseThrow(() -> new RuntimeException("Medidor no encontrado para la residencia"));

                meter.setNumberMeter(residenceDto.getNumberMeter());
                meter.setIdFee(residenceDto.getIdFee());
                meter.setDateUpdate(LocalDateTime.now());
                meter.setIdUserUpdate(currentUser.getUser().getIdUser());
            } else {
                // Crear un nuevo medidor si no existe
                meter = new Meter();
                meter.setNumberMeter(residenceDto.getNumberMeter());
                meter.setIdFee(residenceDto.getIdFee());
                meter.setDateRegister(LocalDateTime.now());
                meter.setDateUpdate(LocalDateTime.now());
                meter.setIdUserRegister(currentUser.getUser().getIdUser());
                meter.setIdUserUpdate(currentUser.getUser().getIdUser());
            }
            // Guardar el medidor y asociar el ID a la residencia
            meterRepository.save(meter);
            residence.setIdMeter(meter.getIdMeter());
        }
        residenceRepository.save(residence);
    }

}
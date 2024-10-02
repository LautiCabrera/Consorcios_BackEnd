package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Service.ModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/modalities")
public class ModalityController {

    @Autowired
    private ModalityService modalityService;

    @GetMapping
    public WebApiResponse<List<Modality>> getAllModalities() {
        try {
            List<Modality> modalities = modalityService.getAllModalities();
            return WebApiResponse.success(modalities, "Modalidades obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidades", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/{id}")
    public WebApiResponse<Modality> getModalityById(@PathVariable Long id) {
        try {
            return WebApiResponse.success(modalityService.getModalityById(id), "Modalidad obtenida exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping
    public WebApiResponse<Void> createModality(@RequestBody Modality modality) {
        try {
            modalityService.createModality(modality);
            return WebApiResponse.success(null, "Modalidad creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/{id}")
    public WebApiResponse<Void> updateModality(@PathVariable Long id, @RequestBody Modality modality) {
        try {
            modality.setIdModality(id);
            modalityService.updateModality(modality);
            return WebApiResponse.success(null, "Modalidad actualizada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @DeleteMapping("/{id}")
    public WebApiResponse<Void> deleteModality(@PathVariable Long id) {
        try {
            modalityService.deleteModality(id);
            return WebApiResponse.success(null, "Modalidad eliminada exitosamente", HttpStatus.NO_CONTENT.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al eliminar modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
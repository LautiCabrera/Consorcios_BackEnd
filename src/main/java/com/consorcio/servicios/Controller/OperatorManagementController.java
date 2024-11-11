package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Dto.FeeDto;
import com.consorcio.servicios.Dto.Read.*;
import com.consorcio.servicios.Dto.ReadingDto;
import com.consorcio.servicios.Dto.ResidenceDto;
import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.Location;
import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Service.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/operator")
public class OperatorManagementController {

    @Autowired
    private UserService userManagementService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ReadingService readingService;
    @Autowired
    private ModalityService modalityService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private BillService billService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ResidenceService residenceService;

    // Gestion de usuarios
    @GetMapping("/users")
    public WebApiResponse<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userManagementService.getAllUsers();
            return WebApiResponse.success(users, "Usuarios obtenidos exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener usuarios", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/users-active")
    public WebApiResponse<List<UserDto>> getUsersActives() {
        try {
            List<UserDto> users = userManagementService.getUsersActives(Role.ROLE_USER, UserStatus.ACTIVE);
            return WebApiResponse.success(users, "Usuarios activos obtenidos exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener usuarios activos", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-operator")
    public WebApiResponse<Void> operatorRegister(@RequestBody RegisterRequestDto request) {
        try {
            authService.register(request, Role.ROLE_OPERATOR);
            return WebApiResponse.success(null, "Registro exitoso", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al registrar operador", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-user")
    public WebApiResponse<Void> userRegister(@RequestBody RegisterRequestDto request) {
        try {
            authService.register(request, Role.ROLE_USER);
            return WebApiResponse.success(null, "Registro exitoso", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al registrar usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update-user")
    public WebApiResponse<Void> updateUser(@RequestParam("idUser") Long idUser, @RequestBody UserDto userDto) {
        try {
            userManagementService.updateUser(idUser, userDto);
            return WebApiResponse.success(null, "Actualización exitosa", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/toggle-activation")
    public WebApiResponse<Void> toggleActivation(@RequestParam("id") Long id,
            @RequestParam("status") UserStatus status) {
        try {
            userManagementService.changeUserStatus(id, status);
            return WebApiResponse.success(null, "Cambio de estado exitoso", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al cambiar estado del usuario", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestión de lecturas del usuario
    @GetMapping("/readings")
    public WebApiResponse<List<ReadReadingDto>> getAllReadings() {
        try {
            List<ReadReadingDto> readings = readingService.getAllReadings();
            return WebApiResponse.success(readings, "Lecturas obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener lecturas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/readings/{idUser}")
    public WebApiResponse<List<ReadReadingDto>> getReadingsByUserId(@PathVariable long idUser) {
        try {
            List<ReadReadingDto> readings = readingService.getReadingsByUserId(idUser);
            return WebApiResponse.success(readings, "Lecturas obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener lecturas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-reading/{idUser}")
    public WebApiResponse<Void> createReading(@PathVariable long idUser, @RequestBody ReadingDto reading) {
        try {
            readingService.createReading(idUser, reading);
            return WebApiResponse.success(null, "Lectura creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear lectura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update-reading/{idReading}")
    public WebApiResponse<Void> updateReading(@PathVariable long idReading, @RequestBody ReadingDto reading) {
        try {
            readingService.updateReading(idReading, reading);
            return WebApiResponse.success(null, "Lectura actualizada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar lectura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestion de Modalidades
    @GetMapping("/modalities")
    public WebApiResponse<List<Modality>> getAllModalities() {
        try {
            List<Modality> modalities = modalityService.getAllModalities();
            return WebApiResponse.success(modalities, "Modalidades obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidades", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/modality/{idModality}")
    public WebApiResponse<Modality> getModalityById(@PathVariable Long idModality) {
        try {
            return WebApiResponse.success(modalityService.getModalityById(idModality),
                    "Modalidad obtenida exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-modality")
    public WebApiResponse<Void> createModality(@RequestBody Modality modality) {
        try {
            modalityService.createModality(modality);
            return WebApiResponse.success(null, "Modalidad creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update-modality/{idModality}")
    public WebApiResponse<Void> updateModality(@PathVariable Long idModality, @RequestBody Modality modality) {
        try {
            modality.setIdModality(idModality);
            modalityService.updateModality(modality);
            return WebApiResponse.success(null, "Modalidad actualizada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @DeleteMapping("/modality/{idModality}")
    public WebApiResponse<Void> deleteModality(@PathVariable Long idModality) {
        try {
            modalityService.deleteModality(idModality);
            return WebApiResponse.success(null, "Modalidad eliminada exitosamente", HttpStatus.NO_CONTENT.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al eliminar modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestion de Periodos
    @GetMapping("/period/{idModality}")
    public WebApiResponse<List<ReadPeriodDto>> getPeriodByIdModality(@PathVariable Long idModality) {
        try {
            List<ReadPeriodDto> periods = periodService.getPeriodByModalityId(idModality);
            return WebApiResponse.success(periods, "Periodos obtenidos exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener periodos", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/periods-actives")
    public WebApiResponse<List<ReadPeriodDto>> getPeriodsActive() {
        try {
            List<ReadPeriodDto> periods = periodService.getPeriodsActives();
            return WebApiResponse.success(periods, "Periodos obtenidos exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener periodos", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    
    // Gestion de facturas 
    @PostMapping("/bill/generate/{idUser}/{idPeriod}")
    public WebApiResponse<Void> generateBill(@PathVariable Long idUser, @PathVariable Long idPeriod) {
        try {
            billService.generateBill(idUser, idPeriod);
            return WebApiResponse.success(null, "Factura generada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al generar la factura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/bill/generate/{idPeriod}")
    public WebApiResponse<Void> generateBillForAllMeter(@PathVariable Long idPeriod) {
        try {
            billService.generateBillForAllMeters(idPeriod);
            return WebApiResponse.success(null, "Facturas generadas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al generar las facturas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/bill/{idUser}/{idPeriod}")
    public WebApiResponse<BillDto> getBillByIdUserAndIdPeriod(@PathVariable Long idUser, @PathVariable Long idPeriod) {
        try {
            BillDto bill = billService.getBillByUserAndPeriod(idUser, idPeriod);
            return WebApiResponse.success(bill, "Datos de factura enviados exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al enviar datos de factura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @GetMapping("/payment/{idUser}")
    public WebApiResponse<List<PaymentDto>> getPaymentsByUserId(@PathVariable Long idUser) {
        try {
            List<PaymentDto> payments = billService.getPaymentsByUserId(idUser);
            return WebApiResponse.success(payments, "Datos de pagos enviados exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al enviar datos de pagos", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/payment/{idBill}")
    public WebApiResponse<Void> changePaymentStatus(@PathVariable Long idBill) {
        try {
            billService.changePaymentStatusByBillId(idBill);
            return WebApiResponse.success(null, "Cambio de estado de pago realizado exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al cambiar el estado del pago", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    //Gestion de tarifas
    @GetMapping("/fee")
    public WebApiResponse<List<FeeDto>> getAllFee() {
        try {
            return WebApiResponse.success(feeService.getAllFee(), "Tarifas obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener tarifas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    //Gestion de localidades
    @GetMapping("/locations")
    public WebApiResponse<List<Location>> getProvinceLocations() {
        try {
            return WebApiResponse.success(locationService.getLocationsByProvince(14L), "Locaciones obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener locaciones", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    //Gestion de Residencias y medidores
    @GetMapping("/residences")
    public WebApiResponse<List<ReadResidenceDto>> getAllResidences() {
        try {
            return WebApiResponse.success(residenceService.getAllResidences(), "Residencias obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener residencias", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-residence")
    public WebApiResponse<Void> createResidence(@RequestBody ResidenceDto residenceDto) {
        try {
            residenceService.createResidence(residenceDto);
            return WebApiResponse.success(null, "Residencia creada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear residencia", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update-residence/{idResidence}")
    public WebApiResponse<Void> updateResidence(@PathVariable Long idResidence, @RequestBody ResidenceDto residenceDto) {
        try {
            residenceService.updateResidence(idResidence, residenceDto);
            return WebApiResponse.success(null, "Residencia actualizada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al actualizar residencia", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
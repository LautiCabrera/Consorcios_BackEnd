package com.consorcio.servicios.Controller;

import com.consorcio.servicios.Dto.Create.CreateReadingDto;
import com.consorcio.servicios.Dto.Read.ReadPeriodDto;
import com.consorcio.servicios.Dto.Update.UpdateReadingDto;
import com.consorcio.servicios.Dto.UserDto;
import com.consorcio.servicios.Entity.Bill;
import com.consorcio.servicios.Entity.Modality;
import com.consorcio.servicios.Entity.Reading;
import com.consorcio.servicios.Enums.UserStatus;
import com.consorcio.servicios.Security.Config.WebApiResponse;
import com.consorcio.servicios.Security.Dto.RegisterRequestDto;
import com.consorcio.servicios.Security.Enums.Role;
import com.consorcio.servicios.Security.Service.AuthService;
import com.consorcio.servicios.Service.BillService;
import com.consorcio.servicios.Service.ModalityService;
import com.consorcio.servicios.Service.PeriodService;
import com.consorcio.servicios.Service.ReadingService;
import com.consorcio.servicios.Service.UserService;
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
    public WebApiResponse<List<Reading>> getAllReadings() {
        try {
            List<Reading> readings = readingService.getAllReadings();
            return WebApiResponse.success(readings, "Lecturas obtenidas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener lecturas", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/register-reading/{idUser}")
    public WebApiResponse<Void> createReading(@PathVariable long idUser, @RequestBody CreateReadingDto reading) {
        try {
            readingService.createReading(idUser, reading);
            return WebApiResponse.success(null, "Lectura creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear lectura", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PutMapping("/update-reading/{idReading}")
    public WebApiResponse<Void> updateReading(@PathVariable long idReading, @RequestBody UpdateReadingDto reading) {
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
            return WebApiResponse.success(modalityService.getModalityById(idModality), "Modalidad obtenida exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/modality-register")
    public WebApiResponse<Void> createModality(@RequestBody Modality modality) {
        try {
            modalityService.createModality(modality);
            return WebApiResponse.success(null, "Modalidad creada exitosamente", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al crear modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/modality/{idModality}")
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
            return WebApiResponse.success(periods, "Modalidad obtenida exitosamente",
                    HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al obtener modalidad", e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Gestion de Medidores
    // Gestion de facturas 
    @PostMapping("/bill/{idMeter}/{idPeriod}")
    public WebApiResponse<Bill> generateBill(@PathVariable Long idMeter, @PathVariable Long idPeriod) {
        try {
            Bill bill = billService.generateBill(idMeter, idPeriod);
            return WebApiResponse.success(bill, "Factura generada exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al generar la factura", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @PostMapping("/bill/{idPeriod}")
    public WebApiResponse<List<Bill>> generateBillForAllMeter(Long idPeriod) {
        try {
            List <Bill> bill = billService.generateBillForAllMeters(idPeriod);
            return WebApiResponse.success(bill, "Facturas generadas exitosamente", HttpStatus.OK.value());
        } catch (Exception e) {
            return WebApiResponse.error("Error al generar las facturas", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}

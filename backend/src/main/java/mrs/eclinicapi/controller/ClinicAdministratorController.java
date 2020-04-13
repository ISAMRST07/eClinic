package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.UserRepositroy;
import mrs.eclinicapi.service.ClinicAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/clinicadministrator")
public class ClinicAdministratorController {

    private ClinicAdministratorService clinisAService;

    @Autowired
    public ClinicAdministratorController(ClinicAdministratorService clinisAService) {
        this.clinisAService = clinisAService;
    }

    @PostMapping()
    public ClinicAdministratorDto addClinicAdministrator(@RequestBody ClinicAdministratorDto clinicAdministratorDto ) {
        return new ClinicAdministratorDto(clinisAService.addClinicAdministraor(clinicAdministratorDto));

    }

}

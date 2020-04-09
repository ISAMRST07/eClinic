package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.ClinicAdministrator;
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

    @Autowired
    ClinicAdministratorService clinisAService;


    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean addClinicAdministrato(@RequestBody ClinicAdministrator newClinicAdmin ) {
        boolean greetings = clinisAService.addClinicAdministraor(newClinicAdmin);
        return  greetings;
    }

}

package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.service.ClinicAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/clinicadministrator")
public class ClinicAdministratorController {

    @Autowired
    ClinicAdministratorService clinisAService;


    @PostMapping("/")
    public boolean addClinicAdministrato(@RequestBody ClinicAdministrator newClinicAdmin ) {
        boolean greetings = clinisAService.addClinicAdministraor(newClinicAdmin);
        return  greetings;
    }

}

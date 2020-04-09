package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Clinic>> getAll() {
        return new ResponseEntity<>(clinicService.getAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> createGreeting(Clinic clinic) throws Exception {
        Clinic addedClinic = clinicService.create(clinic);
        if (addedClinic == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(addedClinic, HttpStatus.CREATED);
    }
}

package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

    @Autowired
    private ClinicService service;

    @Autowired
    private DoctorService doctorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> addClinic(@RequestBody Clinic clinic) {
        Clinic newClinic = service.addClinic(clinic);
        if (newClinic == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        System.out.println("newClinic = " + clinic);
        return new ResponseEntity<>(newClinic, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> modifyClinic(@RequestBody Clinic clinic) {
        Clinic modified = service.modifyClinic(clinic);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Clinic>> getAllClinic() {

        List<Clinic> clinics = service.findAll();

        if (clinics == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Clinic c : clinics) {
            System.out.println("getall clinics = " + c);
        }
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Clinic> getOneClinic(@PathVariable("id") String id) {
        System.out.println("getOneClinic id = " + id);

        Clinic clinic = service.findOne(id);
        System.out.println("returned to controller = " + clinic);
        if (clinic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("get one  clinics = " + clinic);
        return new ResponseEntity<>(clinic, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClinic(@PathVariable("id") String id) {
        Clinic clinic = service.findOne(id);
        if (clinic == null) {
            return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted clinic", HttpStatus.OK);
    }

}

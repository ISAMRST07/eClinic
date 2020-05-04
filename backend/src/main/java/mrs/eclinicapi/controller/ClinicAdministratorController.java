package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.service.ClinicAdministratorService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinicadministrator")
public class ClinicAdministratorController {

    @Autowired
    private ClinicAdministratorService service;
    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClinicAdministratorDto addClinicAdministrator(@RequestBody ClinicAdministratorDto clinicAdministratorDto) {
        return service.addClinicAdministraor(clinicAdministratorDto);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClinicAdministrator(@PathVariable("id") String id) {
        ClinicAdministrator clinicAdministrator = service.findById(id);
        System.out.println(clinicAdministrator);
//        if (clinicAdministrator == null) {
//            return new ResponseEntity<>("clinicAdministrator not found", HttpStatus.NOT_FOUND);
//        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted clinicAdministrator", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ClinicAdministratorDto modifyClinic(@RequestBody ClinicAdministratorDto clinicAdministratorDto) {
        return service.updateClinicAdministraor(clinicAdministratorDto);

    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ClinicAdministratorDto>> getAllClinicAdmin() {
        System.out.println("USA SAM ODJE");
        List<ClinicAdministrator> clinicAdministrators = service.findAll();
        List<ClinicAdministratorDto> clinicAdministratorsDto = new ArrayList<>();
        if (clinicAdministrators == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (ClinicAdministrator c : clinicAdministrators) {
            clinicAdministratorsDto.add(new ClinicAdministratorDto(c, 0));
        }
        return new ResponseEntity<>(clinicAdministratorsDto, HttpStatus.OK);
    }

}

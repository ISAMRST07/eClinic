package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.ClinicAdministratorDto;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.UserRepository;
import mrs.eclinicapi.service.ClinicAdministratorService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "api/clinicadministrator")
public class ClinicAdministratorController {

	@Autowired
    private ClinicAdministratorService service;
	@Autowired
    private UserService userService;

    @PostMapping()
    public ClinicAdministratorDto addClinicAdministrator(@RequestBody ClinicAdministratorDto clinicAdministratorDto ) {
        return new ClinicAdministratorDto(service.addClinicAdministraor(clinicAdministratorDto));

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteClinicAdministrator(@PathVariable("id") Long id) {
        ClinicAdministrator clinicAdministrator = service.findById(id);
        System.out.println(clinicAdministrator);
//        if (clinicAdministrator == null) {
//            return new ResponseEntity<>("clinicAdministrator not found", HttpStatus.NOT_FOUND);
//        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted clinicAdministrator", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ClinicAdministratorDto modifyClinic(@RequestBody ClinicAdministratorDto clinicAdministratorDto) {
        return new ClinicAdministratorDto(service.updateClinicAdministraor(clinicAdministratorDto));

    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ClinicAdministratorDto>> getAllClinicAdmin() {
        System.out.println("USA SAM ODJE");
        List<ClinicAdministrator> clinicAdministrators = service.findAll();
        List<ClinicAdministratorDto> clinicAdministratorsDto = new ArrayList<>();
        if (clinicAdministrators == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for(ClinicAdministrator c : clinicAdministrators) {
            clinicAdministratorsDto.add(new ClinicAdministratorDto(c));
        }
        return new ResponseEntity<>(clinicAdministratorsDto, HttpStatus.OK);
    }

}

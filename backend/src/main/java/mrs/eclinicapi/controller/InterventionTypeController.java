package mrs.eclinicapi.controller;

import mrs.eclinicapi.dto.InterventionTypeDTO;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.InterventionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/interventionType")
public class InterventionTypeController {

    @Autowired
    private InterventionTypeService service;

    @Autowired
    private ClinicService clinicService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionType> addInterventionType(@RequestBody InterventionTypeDTO itDTO) {

        InterventionType newIT = new InterventionType();
        newIT.setName(itDTO.getName());
        newIT.setClinic(clinicService.findOne(itDTO.getClinic()));
        newIT.setPrice(itDTO.getPrice());

        InterventionType added = service.add(newIT);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterventionType>> getAllInterventionTypes() {

        List<InterventionType> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterventionType>> getClinicInterventionType(@PathVariable("id") String clinicId) {

        List<InterventionType> it = service.getClinicInterventionType(clinicId);

        return new ResponseEntity<>(it, HttpStatus.OK);
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionType> modifyInterventionType(@RequestBody InterventionTypeDTO itDTO) {

        InterventionType newIT = service.findOne(itDTO.getId());
        newIT.setPrice(itDTO.getPrice());
        newIT.setName(itDTO.getName());
        InterventionType modified = service.add(newIT);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteInterventionType(@PathVariable("id") String id) {

        InterventionType it = service.findOne(id);

        if (it == null) {
            return new ResponseEntity<>("InterventionType not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted InterventionType", HttpStatus.OK);
    }

}

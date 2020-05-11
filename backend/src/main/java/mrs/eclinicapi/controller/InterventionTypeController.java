package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.InterventionTypeDTO;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.InterventionTypeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/interventionType")
public class InterventionTypeController {

    @Autowired
    private InterventionTypeService service;

    @Autowired
    private ClinicService clinicService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionType> addInterventionType(@RequestBody InterventionTypeDTO itDTO) {
    	System.out.println("addInterventionType dto = " + itDTO);
    	InterventionType newIT = new InterventionType();
    	newIT.setName(itDTO.getName());
    	newIT.setClinic(clinicService.findOne(itDTO.getClinic()));
    	newIT.setPrice(itDTO.getPrice());
    	System.out.println("added interventiontype = " + newIT);
    	InterventionType added = service.add(newIT);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterventionType>> getAllInterventionTypes() {
    	System.out.println("getAllInterventionTypes");
        List<InterventionType> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InterventionType>> getClinicInterventionType(@PathVariable("id") String clinicId) {
    	System.out.println("getClinicInterventionType clinicId = " + clinicId);
    	List<InterventionType> it = service.getClinicInterventionType(clinicId);
    	System.out.println("getClinicInterventionType it = " + it);
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionType> modifyInterventionType(@RequestBody InterventionTypeDTO itDTO) {
    	System.out.println("modifyInterventionType itDTO = " + itDTO);
    	InterventionType newIT = service.findOne(itDTO.getId());
    	newIT.setPrice(itDTO.getPrice());
    	newIT.setName(itDTO.getName());
    	InterventionType modified = service.add(newIT);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	System.out.println("modifyInterventionType modified = " + modified);
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteInterventionType(@PathVariable("id") String id) {
    	System.out.println("deleteInterventionType id = " + id);
    	InterventionType it = service.findOne(id);
    	System.out.println("deleteInterventionType it = " + it);
        if (it == null) {
            return new ResponseEntity<>("InterventionType not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted InterventionType", HttpStatus.OK);
    }

}

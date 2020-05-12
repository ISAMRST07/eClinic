package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.InterventionDTO;
import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.service.InterventionService;

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
@RequestMapping(value = "api/intervention")
public class InterventionController {

    @Autowired
    private InterventionService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Intervention> addIntervention(@RequestBody InterventionDTO iDTO) {
    	System.out.println("addIntervention dto = " + iDTO);
    	/*Intervention newIntervention = new Intervention();
    	//newIntervention.setDateTime(iDTO.getDateTime());
    	newIntervention.setClinicRoom(iDTO.getSelectedClinicRoom());
    	newIntervention.setDoctor(iDTO.getSelectedDoctor());
    	newIntervention.setInterventionType(iDTO.getSelectedInterventionType());
    	newIntervention.setDuration(iDTO.getDuration());
    	newIntervention.setPrice(iDTO.getPrice());

    	System.out.println("added newIntervention = " + newIntervention);
    	Intervention added = service.add(newIntervention);*/
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getAllIntervention() {
    	System.out.println("getAllIntervention");
        List<Intervention> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }
    
    /*@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getClinicIntervention(@PathVariable("id") String clinicId) {
    	System.out.println("getClinicIntervention clinicId = " + clinicId);
    	List<Intervention> it = service.getClinicInterventionType(clinicId);
    	System.out.println("getClinicInterventionType it = " + it);
        return new ResponseEntity<>(it, HttpStatus.OK);
    }*/
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Intervention> modifyIntervention(@RequestBody InterventionDTO iDTO) {
    	System.out.println("modifyIntervention iDTO = " + iDTO);
    	Intervention newIT = service.findOne(iDTO.getId());
    	/*newIT.setClinicRoom(iDTO.getSelectedClinicRoom());
    	newIT.setDoctor(iDTO.getSelectedDoctor());
    	newIT.setInterventionType(iDTO.getSelectedInterventionType());
    	newIT.setDuration(iDTO.getDuration());
    	newIT.setPrice(iDTO.getPrice());*/

    	
    	Intervention modified = service.add(newIT);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	System.out.println("modifyIntervention modified = " + modified);
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteIntervention(@PathVariable("id") String id) {
    	System.out.println("deleteIntervention id = " + id);
    	Intervention it = service.findOne(id);
    	System.out.println("deleteIntervention it = " + it);
        if (it == null) {
            return new ResponseEntity<>("Interventionnot found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted Intervention", HttpStatus.OK);
    }

}

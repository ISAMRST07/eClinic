package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.InterventionDTO;
import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    	Intervention newIntervention = new Intervention();
    	newIntervention.setClinicRoom(iDTO.getSelectedClinicRoom());
    	newIntervention.setDoctor(iDTO.getSelectedDoctor());
    	newIntervention.setInterventionType(iDTO.getSelectedInterventionType());
    	newIntervention.setDuration(iDTO.getDuration());
    	newIntervention.setPrice(iDTO.getPrice());

    	System.out.println("added newIntervention = " + newIntervention);
    	Intervention added = service.add(newIntervention);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }
}

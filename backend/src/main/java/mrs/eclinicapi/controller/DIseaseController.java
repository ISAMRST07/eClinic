package mrs.eclinicapi.controller;

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

import mrs.eclinicapi.dto.InterventionTypeDTO;
import mrs.eclinicapi.model.Diagnosis;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.service.DiagnosisService;

@RestController
@RequestMapping(value = "api/disease")
public class DIseaseController {

    @Autowired
    DiagnosisService service;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnosis>> getAllDiseases() {
        System.out.println("getAllDiseases");
        List<Diagnosis> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis d) {
        System.out.println("addDiagnosis = " + d);
        Diagnosis added = service.add(d);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> modifyDisease(@RequestBody Diagnosis d) {
        System.out.println("modifyDisease d = " + d);
        Diagnosis newD = service.findOne(d.getId());
        newD.setName(d.getName());
        Diagnosis modified = service.add(newD);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("modifyDisease modified = " + modified);
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDisease(@PathVariable("id") String id) {
        System.out.println("deleteDisease id = " + id);
        Diagnosis d = service.findOne(id);
        System.out.println("deleteDisease it = " + d);
        if (d == null) {
            return new ResponseEntity<>("Disease not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted InterventionType", HttpStatus.OK);
    }
}

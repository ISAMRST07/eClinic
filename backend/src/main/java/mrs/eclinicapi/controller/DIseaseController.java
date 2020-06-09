package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Diagnosis;
import mrs.eclinicapi.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/disease")
public class DIseaseController {

    @Autowired
    DiagnosisService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diagnosis>> getAllDiseases() {

        List<Diagnosis> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> addDiagnosis(@RequestBody Diagnosis d) {

        Diagnosis added = service.add(d);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diagnosis> modifyDisease(@RequestBody Diagnosis d) {

        Diagnosis newD = service.findOne(d.getId());
        newD.setName(d.getName());
        Diagnosis modified = service.add(newD);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDisease(@PathVariable("id") String id) {

        Diagnosis d = service.findOne(id);

        if (d == null) {
            return new ResponseEntity<>("Disease not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted InterventionType", HttpStatus.OK);
    }
}

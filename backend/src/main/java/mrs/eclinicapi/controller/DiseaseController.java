package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Disease;
import mrs.eclinicapi.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disease>> getAllDisease() {
        List<Disease> diseases = service.findAll();
        return new ResponseEntity<>(diseases, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disease> addDisease(@RequestBody Disease disease) {
        Disease added = service.add(disease);
        return new ResponseEntity<>(added, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disease> updateDisease(@RequestBody Disease disease) {
        Disease modified = service.modify(disease);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDisease(@PathVariable("id") String id) {

        Disease disease = service.findOne(id);
        if (disease == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }

        service.deleteById(id);
        return new ResponseEntity<>("room deleted", HttpStatus.OK);
    }

}

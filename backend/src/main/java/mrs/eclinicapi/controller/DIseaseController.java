package mrs.eclinicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.model.Diagnosis;
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

}

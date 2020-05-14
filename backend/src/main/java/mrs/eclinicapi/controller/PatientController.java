package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.MedicalRecord;
import mrs.eclinicapi.model.Patient;
import mrs.eclinicapi.service.MedicalRecordService;
import mrs.eclinicapi.service.MedicineService;
import mrs.eclinicapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private MedicalRecordService medicalRecordService;
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getPatients() {

        List<Patient> patients = service.getPatients();

        if (patients == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping(path = "/create-record/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> createMedicalRecord(@PathVariable String id) {
        Patient found = service.getPatientById(id);
        if (found == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        MedicalRecord newMedicalRecord = new MedicalRecord();

        medicalRecordService.save(newMedicalRecord);

        found.setMedicalRecord(newMedicalRecord);

        Patient saved = service.addPatient(found);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public  ResponseEntity<Patient> deletePatient(@PathVariable String id) {
        try {
            Patient p = service.deletePatient(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}

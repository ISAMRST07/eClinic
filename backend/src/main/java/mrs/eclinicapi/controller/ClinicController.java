package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.service.ClinicService;

import java.util.Iterator;
import java.util.List;

import mrs.eclinicapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

	@Autowired
	private ClinicService service;

	@Autowired
	private DoctorService doctorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Clinic> addClinic(@RequestBody Clinic clinic) {
    	Clinic newClinic = service.addClinic(clinic);
    	if (newClinic == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
    	System.out.println("newClinic = " + clinic);
        return new ResponseEntity<>(newClinic, HttpStatus.OK);
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clinic> modifyClinic(@PathVariable("id") Long id, @RequestBody Clinic clinic) {

		Clinic modified = service.modifyClinic(id, clinic);
		if (modified == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		return new ResponseEntity<>(modified, HttpStatus.OK);
	}

    @GetMapping(value = "/getAll")
   	public ResponseEntity<List<Clinic>> getAllClinic() {

   		List<Clinic> clinics = service.findAll();

   		if (clinics == null) {
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
   		for(Clinic c : clinics) {
   	   		System.out.println("getall clinics = " + c);
   		}
   		return new ResponseEntity<>(clinics, HttpStatus.OK);
   	}

    @DeleteMapping(path="/delete/{id}")
	public ResponseEntity<String> deleteClinic(@PathVariable("id") Long id) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);
   		return new ResponseEntity<>("deleted clinic", HttpStatus.OK);
	}
	
}

package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.service.ClinicService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

	@Autowired
	private ClinicService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Clinic addClinic(@RequestBody Clinic clinic) {
    	service.addClinic(clinic);
    	System.out.println("newClinic = " + clinic);
        return clinic;
    }

    @RequestMapping(path="/{id}")
	public Clinic getClinic(@PathVariable("id") Long id) {

		Clinic clinic = service.findOne(id);

		if (clinic == null) {
			return null;
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		return clinic;
	}

    @GetMapping(value = "/getAll")
   	public ResponseEntity<List<Clinic>> getAllClinic() {

   		List<Clinic> clinics = service.findAll();

   		if (clinics == null) {
   			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   		}
   		for(Clinic c : clinics) {
   	   		System.out.println("getall clinic = " + c);
   		}
   		return new ResponseEntity<>(clinics, HttpStatus.OK);
   	}
    
    @RequestMapping(path="/delete/{id}")
	public ResponseEntity<String> deleteClinic(@PathVariable("id") Long id) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		service.deleteById(id);
   		return new ResponseEntity<>("deleted clinic", HttpStatus.OK);
	}
    
    @RequestMapping(path="/updateName")
	public ResponseEntity<String> updateName(@RequestParam Long id,
											@RequestParam String newName) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		service.updateName(id, newName);
   		return new ResponseEntity<>("updated clinic", HttpStatus.OK);
	}
    
    
    @RequestMapping(path="/updateDescription")
	public ResponseEntity<String> updateDescription(@RequestParam Long id,
													@RequestParam String newDescription) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		service.updateDescription(id, newDescription);
   		return new ResponseEntity<>("updated clinic", HttpStatus.OK);
	}
    
    @RequestMapping(path="/updateAdress")
	public ResponseEntity<String> updateAdress(@RequestParam Long id,
												@RequestParam String newAddress) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		service.updateAddress(id, newAddress);
   		return new ResponseEntity<>("updated clinic", HttpStatus.OK);
	}
    
    @RequestMapping(path="/deleteDoctor")
	public ResponseEntity<String> deleteDoctor(@RequestParam Long id,
												@RequestParam Long doctorId) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		//service.updateAddress(id, newAddress);
		for(Doctor d : clinic.getDoctors()) {
			System.out.println("doctor d = " + d);
		}
   		return new ResponseEntity<>("delete doctor clinic", HttpStatus.OK);
	}
    
}

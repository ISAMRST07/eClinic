package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
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

    @PutMapping(path="/{id}")
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

    @DeleteMapping(path="/{id}")
	public ResponseEntity<String> deleteClinic(@PathVariable("id") Long id) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("found clinic with id = " + id + " " + clinic);
		service.deleteById(id);
   		return new ResponseEntity<>("deleted clinic", HttpStatus.OK);
	}

}

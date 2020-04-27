package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.service.ClinicService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

	@Autowired
	private ClinicService service;
    
    @PostMapping()
    public Clinic addClinic(@RequestParam String name, @RequestParam String id) {
    	Clinic newClinic = new Clinic();
    	newClinic.setName(name);
    	service.addClinic(newClinic);
    	System.out.println("newClinic = " + newClinic);
        return newClinic;
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
    
}

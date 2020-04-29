package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;

import java.util.Iterator;
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

	@Autowired
	private DoctorService doctorService;

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
		clinic.setName(newName);
		service.addClinic(clinic);
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
		clinic.setDescription(newDescription);
		service.addClinic(clinic);
   		return new ResponseEntity<>("updated clinic", HttpStatus.OK);
	}
    
    @RequestMapping(path="/updateAddress")
	public ResponseEntity<String> updateAddress(@RequestParam Long id,
												@RequestParam String newAddress) {
		Clinic clinic = service.findOne(id);
		if (clinic == null) {
	   		return new ResponseEntity<>("clinic not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("foudn clinic with id = " + id + " " + clinic);
		clinic.setAddress(newAddress);
		service.addClinic(clinic);
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
		
		for (Iterator<Doctor> iterator = clinic.getDoctors().iterator(); iterator.hasNext();) {
		    Doctor d =  iterator.next();
			System.out.println("doctor d = " + d.getId());
		    if (d.getId() == doctorId) {
				System.out.println("doctor delete this");
		    	iterator.remove();
		    }       
		}
		System.out.println("clinic doctors after delete");
		for(Doctor d : clinic.getDoctors()) {
			System.out.println("doctor d = " + d.getId());
		}
		
		service.addClinic(clinic);
   		Doctor d = doctorService.findOne(doctorId);
   		d.setClinic(null);
   		doctorService.addDoctor(d);
   		
		return new ResponseEntity<>("delete doctor clinic", HttpStatus.OK);
	}
    
}

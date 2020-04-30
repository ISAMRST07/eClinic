package mrs.eclinicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.Nurse;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.NurseService;
import mrs.eclinicapi.service.UserService;

@RestController
@RequestMapping(value = "api/nurse")
public class NurseController {

	@Autowired
	private NurseService service;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ClinicService clinicService;

	
	@RequestMapping(path="/addNurse")
    public Nurse addNurse(@RequestParam String position,
    						@RequestParam Long userId,
    						@RequestParam Long clinicId) {
		
		User user = userService.findOne(userId);
		if(user == null) {
			System.out.println("user with id = " + userId + " == null");
		}
		System.out.println("found userId = " + userId + " = " + user);
		
		Clinic clinic = clinicService.findOne(clinicId);
		if(clinic == null) {
			System.out.println("clinic with id = " + clinicId + " == null");
		}
		System.out.println("found clinicId = " + clinicId + " = " + clinic);
		
		Nurse nurse = new Nurse(position);
		nurse.setUser(user);
		nurse.setClinic(clinic);
		
		service.addNurse(nurse);
    	System.out.println("newNurse = " + nurse);
        return nurse;
    }

	@RequestMapping(path="/deleteNurse/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable("id") Long id) {
		System.out.println("delete nurse " + id);
	
		Nurse nurse = service.findOne(id);
		if(nurse == null) {
			System.out.println("nurse not found");
   			return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("delete this nurse = " + nurse);

		service.deleteById(id);
		return new ResponseEntity<>("nurse deleted", HttpStatus.OK);
    }
	
	@RequestMapping(path="/getAll")
    public ResponseEntity<List<Nurse>> getAll() {
		System.out.println("get all nurse ");
		List<Nurse> nurseList = service.findAll();
		if(nurseList == null) {
			System.out.println("nurse not found");
   			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nurseList, HttpStatus.OK);
    }
	
	@RequestMapping(path="/getNurse/{id}")
    public ResponseEntity<Nurse> getNurse(@PathVariable("id") Long id) {
		System.out.println("get nurse " + id);
		Nurse nurse = service.findOne(id);
		if(nurse == null) {
			System.out.println("nurse not found");
   			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		System.out.println("get this nurse = " + nurse);

		return new ResponseEntity<>(nurse, HttpStatus.OK);
    }
	
	@RequestMapping(path="/updatePosition")
    public ResponseEntity<String> updatePosition(@RequestParam Long id,
												@RequestParam String newPosition) {
		System.out.println("get nurse " + id);
		Nurse nurse = service.findOne(id);
		if(nurse == null) {
			System.out.println("nurse not found");
   			return new ResponseEntity<>("nurse not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("update this nurse = " + nurse);
		service.updatePosition(id, newPosition);
		return new ResponseEntity<>("positionUpdated", HttpStatus.OK);
    }
	
	@RequestMapping(path="/deleteNurseFromClinic")
    public ResponseEntity<String> deleteNurseFromClinic(@RequestParam Long id) {
		System.out.println("get nurse " + id);
		Nurse nurse = service.findOne(id);
		if(nurse == null) {
			System.out.println("nurse not found");
   			return new ResponseEntity<>("nurse not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("update this nurse = " + nurse);
		nurse.setClinic(null);
		service.addNurse(nurse);
		return new ResponseEntity<>("nurse clinic removed", HttpStatus.OK);
    }
}

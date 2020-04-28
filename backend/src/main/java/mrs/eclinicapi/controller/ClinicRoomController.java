package mrs.eclinicapi.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.service.ClinicRoomService;
import mrs.eclinicapi.service.ClinicService;

@RestController
@RequestMapping(value = "api/clinicroom")
public class ClinicRoomController {

	@Autowired
	private ClinicRoomService service;
	
	@Autowired
	private ClinicService clinicService;
	
	@PostMapping()
    public ClinicRoom addClinicRoom(@RequestParam Long clinicId) {
		System.out.println("adding room to clinic = " + clinicId);
		Clinic clinic = clinicService.findOne(clinicId);
		if(clinic == null) {
			System.out.println("clinic with id = " + clinicId + " is null");
		}else {
			System.out.println("clinic with id = " + clinicId + " is = " + clinic);
		}
		
		ClinicRoom newClinicRoom = new ClinicRoom();
		newClinicRoom.setClinic(clinic);
		service.addClinicRoom(newClinicRoom);
        return newClinicRoom;
    }
	
	@RequestMapping(path="/deleteClinicRoom/{id}")
    public ResponseEntity<String> deleteClinicRoom(@PathVariable("id") Long id) {
		System.out.println("delete clinicroom " + id);
	
		ClinicRoom room = service.findOne(id);
		if(room == null) {
			System.out.println("room not found");
   			return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		}
		System.out.println("delete this room = " + room);

		service.deleteById(id);
		return new ResponseEntity<>("room deleted", HttpStatus.OK);
    }
	
}

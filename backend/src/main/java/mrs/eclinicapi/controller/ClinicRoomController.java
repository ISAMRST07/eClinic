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
import mrs.eclinicapi.service.ClinicRoomService;
import mrs.eclinicapi.service.ClinicService;

@RestController
@RequestMapping(value = "api/clinicroom")
public class ClinicRoomController {

	@Autowired
	private ClinicRoomService clinicRoomService;
	
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
		clinicRoomService.addClinicRoom(newClinicRoom);
        return newClinicRoom;
    }
	/*@GetMapping
	public ResponseEntity<List<ClinicRoom>> getClinicRooms() {
		List<ClinicRoom> clinicRooms = clinicRoomService.getAllClinicRooms();
		return new ResponseEntity<>(clinicRooms, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClinicRoom> getClinicRoom(@PathVariable Long id) {

		ClinicRoom clinicRoom = clinicRoomService.findOneClinicRoom(id);
		// clinicRoom must exist
		if (clinicRoom == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clinicRoom, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json")
	public ResponseEntity<ClinicRoom> saveClinicRoom(@RequestBody ClinicRoom newClinicRoom) {
		ClinicRoom clinicRoom = clinicRoomService.findOneClinicRoom(newClinicRoom.getId());
		//already exists
		if(clinicRoom != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(newClinicRoom, HttpStatus.CREATED);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteClinicRoom(@PathVariable Long id) {

		ClinicRoom clinicRoom = clinicRoomService.findOneClinicRoom(id);

		if (clinicRoom != null) {
			clinicRoomService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
}

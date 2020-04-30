package mrs.eclinicapi.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import mrs.eclinicapi.DTO.ClinicRoomDTO;
import mrs.eclinicapi.model.Intervention;
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

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicRoomDTO> addClinicRoom(@RequestBody ClinicRoomDTO clinicRoomDto) {
		ClinicRoom clinicRoom = this.convertToEntity(clinicRoomDto);
		ClinicRoom added = service.addClinicRoom(clinicRoom);
        return new ResponseEntity<>(this.convertToDTO(added), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClinicRoomDTO>> getAllRooms() {
		List<ClinicRoom> clinicRooms = service.findAll();
		List<ClinicRoomDTO> clinicRoomDTOS = clinicRooms.stream().map(this::convertToDTO).collect(Collectors.toList());
		return new ResponseEntity<>(clinicRoomDTOS, HttpStatus.OK);
	}

	@DeleteMapping(path="/deleteClinicRoom/{id}")
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

    public ClinicRoom convertToEntity(ClinicRoomDTO clinicRoomDTO) {
		Clinic foundClinic = clinicService.findOne(clinicRoomDTO.getClinicId());
		if(foundClinic == null) return null;
		ClinicRoom clinicRoom = new ClinicRoom();
		clinicRoom.setId(clinicRoom.getId());
		clinicRoom.setName(clinicRoomDTO.getName());
		clinicRoom.setClinic(foundClinic);
		Set<Intervention> interventionSet = new HashSet<>(clinicRoomDTO.getPastInterventions());
		interventionSet.addAll(clinicRoomDTO.getScheduledInterventions());
		clinicRoom.setInterventions(interventionSet);
		return clinicRoom;
	}

	public ClinicRoomDTO convertToDTO(ClinicRoom clinicRoom) {
		ClinicRoomDTO clinicRoomDTO = new ClinicRoomDTO();
		clinicRoomDTO.setId(clinicRoom.getId());
		clinicRoomDTO.setName(clinicRoom.getName());
		clinicRoomDTO.setClinicId(clinicRoom.getClinic().getId());
		Set<Intervention> pastInterventions = new HashSet<>();
		Set<Intervention> scheduledInterventions = new HashSet<>();
		for (Intervention i : clinicRoom.getInterventions()) {
			if(i.getDateTime().isBefore(LocalDateTime.now())) pastInterventions.add(i);
			else scheduledInterventions.add(i);
		}
		clinicRoomDTO.setPastInterventions(pastInterventions);
		clinicRoomDTO.setScheduledInterventions(scheduledInterventions);
		if (scheduledInterventions.size() > 0) clinicRoomDTO.setRemovable(false);
		else clinicRoomDTO.setRemovable(true);
		return clinicRoomDTO;
	}
}

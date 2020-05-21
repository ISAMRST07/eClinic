package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.ClinicRoomDTO;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.Intervention;
import mrs.eclinicapi.service.ClinicRoomService;
import mrs.eclinicapi.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClinicRoomDTO>> getOneClinicRooms(@PathVariable("id") String clinicAdminID) {
        List<ClinicRoom> clinicRooms = service.findOneClinicRooms(clinicAdminID);
        List<ClinicRoomDTO> clinicRoomDTOS = clinicRooms.stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(clinicRoomDTOS, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClinicRoomDTO> updateClinicRoom(@RequestBody ClinicRoomDTO clinicRoomDTO) {
        ClinicRoom clinicRoom = this.convertToEntity(clinicRoomDTO);
        ClinicRoom modified = service.modifyRoom(clinicRoom);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.convertToDTO(modified), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClinicRoom(@PathVariable("id") String id) {

        ClinicRoom room = service.findOne(id);
        if (room == null) {
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }

        service.deleteById(id);
        return new ResponseEntity<>("room deleted", HttpStatus.OK);
    }

    public ClinicRoom convertToEntity(ClinicRoomDTO clinicRoomDTO) {
        Clinic foundClinic = clinicService.findOne(clinicRoomDTO.getClinicId());
        if (foundClinic == null) return null;
        ClinicRoom clinicRoom = new ClinicRoom();
        clinicRoom.setId(clinicRoomDTO.getId());
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
            // TODO PREPRAVKA
            if (i.getDateTime().getStart().isBefore(LocalDateTime.now())) pastInterventions.add(i);
            else scheduledInterventions.add(i);
        }
        clinicRoomDTO.setPastInterventions(pastInterventions);
        clinicRoomDTO.setScheduledInterventions(scheduledInterventions);
        if (scheduledInterventions.size() > 0) clinicRoomDTO.setRemovable(false);
        else clinicRoomDTO.setRemovable(true);
        return clinicRoomDTO;
    }
}

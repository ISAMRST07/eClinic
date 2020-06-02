package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.AppointmentRequestDTO;
import mrs.eclinicapi.DTO.EmailEvent;
import mrs.eclinicapi.DTO.InterventionDTO;
import mrs.eclinicapi.model.*;
import mrs.eclinicapi.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping(value = "api/intervention")
public class InterventionController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private InterventionService service;

    @Autowired
    private AppointmentRequestService requestService;

    @Autowired
    private OneClickAppointmentService oneClickAppointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    ClinicRoomService clinicRoomService;

    @Autowired
    ClinicService clinicService;

    @Autowired
    InterventionTypeService interventionTypeService;

    @Autowired
    DoctorService doctorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getAllIntervention() {
    	System.out.println("getAllIntervention");
        List<Intervention> it = service.findAll();
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getClinicIntervention(@PathVariable("id") String clinicId) {
    	System.out.println("getClinicIntervention clinicId = " + clinicId);
    	List<Intervention> it = service.getClinicIntervention(clinicId);
    	System.out.println("getClinicInterventionType it = " + it);
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @PostMapping(path = "/one-click/{oneClickID}/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> scheduleOneClick(@PathVariable String oneClickID,
                                                            @PathVariable String userID) {
        OneClickAppointment appointment = oneClickAppointmentService.findByID(oneClickID);

        if (appointment == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        oneClickAppointmentService.delete(appointment.getId());

        Patient pat = patientService.getByUserId(userID);
        if(pat == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Intervention toAdd = new Intervention(appointment, pat);
        Intervention added = service.add(toAdd);

        String content = "An intervention for the date " + added.getDateTime().getStart() + " has been added." +
                "\r\nYou have an option to refuse coming, via this link: " +
                "\r\nnekilinkcebit.";
        String[] sendToPatient = {added.getPatient().getUser().getEmail()};
        eventPublisher.publishEvent(new EmailEvent(added.getPatient().getUser(), "Appointment scheduled", content, sendToPatient));

        content = "An intervention for the date " + added.getDateTime().getStart() + " has been added.";

        String[] sendToDoctor = {added.getPatient().getUser().getEmail()};
        eventPublisher.publishEvent(new EmailEvent(added.getDoctor().getUser(), "Appointment scheduled", content, sendToDoctor));
        return new ResponseEntity<>(this.convertToDTO(added), HttpStatus.OK);
    }

    @PostMapping(path = "/approve/{requestID}/{roomID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> approve(@PathVariable String requestID,
                                                @PathVariable String roomID) {
        AppointmentRequest request = requestService.findOne(requestID);
        if (request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ClinicRoom room = clinicRoomService.findOne(roomID);
        if (room == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            requestService.delete(request.getId());
        } catch (AppointmentRequestDTO.ConcurrentRequest concurrentRequest) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Intervention toAdd = new Intervention(request, room);
        Intervention added = service.add(toAdd);
        String content = "An intervention for the date " + added.getDateTime().getStart() + " has been added." +
                "\r\nYou have an option to refuse coming, via this link: " +
                "\r\nnekilinkcebit.";
        String[] sendToPatient = {added.getPatient().getUser().getEmail()};
        eventPublisher.publishEvent(new EmailEvent(added.getPatient().getUser(), "Appointment scheduled", content, sendToPatient));

        content = "An intervention for the date " + added.getDateTime().getStart() + " has been added.";

        String[] sendToDoctor = {added.getPatient().getUser().getEmail()};
        eventPublisher.publishEvent(new EmailEvent(added.getDoctor().getUser(), "Appointment scheduled", content, sendToDoctor));


        return new ResponseEntity<>(this.convertToDTO(added), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteIntervention(@PathVariable("id") String id) {
    	System.out.println("deleteIntervention id = " + id);
    	Intervention it = service.findOne(id);
    	System.out.println("deleteIntervention it = " + it);
        if (it == null) {
            return new ResponseEntity<>("Interventionnot found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted Intervention", HttpStatus.OK);
    }


    private InterventionDTO convertToDTO(Intervention intervention) {
        return new InterventionDTO(
                intervention.getId(),
                intervention.getDateTime(),
                intervention.getClinicRoom().getId(),
                intervention.getDoctor().getId(),
                intervention.getInterventionType().getId(),
                intervention.getPatient().getId(),
                intervention.getClinic().getId(),
                intervention.getPrice()
        );
    }

    private Intervention convertToEntity(InterventionDTO interventionDTO) {
        Clinic foundClinic = clinicService.findOne(interventionDTO.getClinicID());
        if(foundClinic == null) return null;
        InterventionType interventionType =
                interventionTypeService.findOne(interventionDTO.getInterventionTypeID());
        if(interventionType == null) return null;
        Doctor doctor = doctorService.findOne(interventionDTO.getDoctorID());
        if (doctor == null) return null;
        Patient patient = patientService.getPatientById(interventionDTO.getPatientID());
        if (patient == null) return null;
        ClinicRoom room = clinicRoomService.findOne(interventionDTO.getClinicRoomID());
        if (room == null) return null;
        return new Intervention(
                interventionDTO.getId(),
                interventionDTO.getDateTime(),
                room,
                patient,
                doctor,
                foundClinic,
                interventionType,
                null,
                interventionDTO.getPrice()
        );
    }

}

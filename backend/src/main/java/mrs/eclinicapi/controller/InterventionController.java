package mrs.eclinicapi.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import mrs.eclinicapi.dto.AppointmentRequestDTO;
import mrs.eclinicapi.dto.EmailEvent;
import mrs.eclinicapi.dto.InterventionDTO;
import mrs.eclinicapi.dto.VisitDTO;
import mrs.eclinicapi.model.*;
import mrs.eclinicapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/intervention")
public class InterventionController {

    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    ClinicRoomService clinicRoomService;
    @Autowired
    ClinicService clinicService;
    @Autowired
    InterventionTypeService interventionTypeService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    VisitService visitService;
    @Autowired
    private InterventionService service;
    @Autowired
    private AppointmentRequestService requestService;
    @Autowired
    private OneClickAppointmentService oneClickAppointmentService;
    @Autowired
    private PatientService patientService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> getIntervention(@PathVariable String id) {
        Intervention it = service.findOne(id);
        if(it == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.convertToDTO(it), HttpStatus.OK);
    }

    @GetMapping(path = "/clinic/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getClinicIntervention(@PathVariable("id") String clinicId) {
        System.out.println("getClinicIntervention clinicId = " + clinicId);
        List<Intervention> it = service.getClinicIntervention(clinicId);
        System.out.println("getClinicInterventionType it = " + it);
        return new ResponseEntity<>(it, HttpStatus.OK);
    }

    @GetMapping(path = "/upcoming/{doctorID}/{patientUserID}")
    public ResponseEntity<InterventionDTO> upcomingIntervention(@PathVariable String doctorID,
                                                                @PathVariable String patientUserID) {
        Patient p = patientService.getByUserId(patientUserID);
        if(p == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Doctor d = doctorService.findByUserID(doctorID);
        if(d == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Intervention upcoming = service.findUpcoming(d.getId(), p.getId());
        if(upcoming == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.convertToDTO(upcoming), HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorID}/{patientUserID")
    public ResponseEntity<Boolean> pastIntervention(@PathVariable String doctorID,
                                                    @PathVariable String patientUserID) {
        Patient p = patientService.getByUserId(patientUserID);
        if(p == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Doctor d = doctorService.findByUserID(doctorID);
        if(d == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        boolean exists = service.pastIntervention(d.getId(), p.getId());
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @PostMapping(path = "/finish/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> finish(@PathVariable String id, @RequestBody VisitDTO visitDTO) {
        Intervention intervention = service.findOne(id);
        if(intervention == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Visit newVisit = new Visit();
        newVisit.setAnamnesis(visitDTO.getAnamnesis());
        newVisit.setDiagnoses(visitDTO.getDiagnoses());
        newVisit.setUsageMethod(visitDTO.getUsageMethod());
        newVisit.setMedicines(visitDTO.getMedicines());
        newVisit.setIntervention(intervention);
        Visit savedVisit = visitService.save(newVisit);

        Patient p = intervention.getPatient();
        p.getMedicalRecord().addVisit(savedVisit);
        p.getMedicalRecord().addDiagnoses(savedVisit.getDiagnoses());
        p.getMedicalRecord().addMedicines(savedVisit.getMedicines());
        intervention.setVisit(savedVisit);
        Intervention saved = service.add(intervention);
        return new ResponseEntity<>(this.convertToDTO(saved), HttpStatus.OK);
    }

    @PostMapping(path = "/one-click/{oneClickID}/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> scheduleOneClick(@PathVariable String oneClickID,
                                                            @PathVariable String userID) {
        OneClickAppointment appointment = oneClickAppointmentService.findByID(oneClickID);

        if (appointment == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        oneClickAppointmentService.delete(appointment.getId());

        Patient pat = patientService.getByUserId(userID);
        if (pat == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @PostMapping(path = "/approve/{requestID}/{roomID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterventionDTO> approve(@PathVariable String requestID,
                                                   @PathVariable String roomID) {
        AppointmentRequest request = requestService.findOne(requestID);
        if (request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ClinicRoom room = clinicRoomService.findOne(roomID);
        if (room == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            requestService.delete(request);
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
        if (foundClinic == null) return null;
        InterventionType interventionType =
                interventionTypeService.findOne(interventionDTO.getInterventionTypeID());
        if (interventionType == null) return null;
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

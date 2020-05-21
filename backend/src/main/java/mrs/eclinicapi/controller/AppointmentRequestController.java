package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.AppointmentRequestDTO;
import mrs.eclinicapi.model.AppointmentRequest;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.service.AppointmentRequestService;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import mrs.eclinicapi.service.InterventionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/appointment-requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentRequestController {

    @Autowired
    AppointmentRequestService service;

    @Autowired
    ClinicService clinicService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    InterventionTypeService interventionTypeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentRequestDTO>> get() {

        return new ResponseEntity<>(service.findAll()
                .stream().map(this::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentRequestDTO> save(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        AppointmentRequest appointmentRequest = this.convertToEntity(appointmentRequestDTO);
        if (appointmentRequest == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(this.convertToDTO(service.save(appointmentRequest)), HttpStatus.OK);
    }

    private AppointmentRequestDTO convertToDTO(AppointmentRequest appointmentRequest) {
        return new AppointmentRequestDTO(
                appointmentRequest.getId(),
                appointmentRequest.getDateTime(),
                appointmentRequest.getInterventionType().getId(),
                appointmentRequest.getClinic().getId(),
                appointmentRequest.getDoctor().getId()
        );
    }

    private AppointmentRequest convertToEntity(AppointmentRequestDTO appointmentRequestDTO) {
        Clinic foundClinic = clinicService.findOne(appointmentRequestDTO.getClinicID());
        if(foundClinic == null) return null;
        InterventionType interventionType =
                interventionTypeService.findOne(appointmentRequestDTO.getInterventionTypeID());
        if(interventionType == null) return null;
        Doctor doctor = doctorService.findOne(appointmentRequestDTO.getDoctorID());
        AppointmentRequest appointmentRequest = new AppointmentRequest(
                appointmentRequestDTO.getId(),
                appointmentRequestDTO.getDateTime(),
                interventionType,
                foundClinic,
                doctor
        );
        return appointmentRequest;
    }
}

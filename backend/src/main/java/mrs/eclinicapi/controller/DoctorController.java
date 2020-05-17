package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.DoctorNurseDTO;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.InterventionType;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import mrs.eclinicapi.service.InterventionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DoctorService service;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private InterventionTypeService interventionTypeService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorNurseDTO> addDoctor(@RequestBody DoctorNurseDTO doctorNurseDTO) {

        Doctor newDoctor = this.convertToEntity(doctorNurseDTO);
        if (newDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.addDoctor(newDoctor);
        //userService.addUser(newUser);


        return new ResponseEntity<>(this.convertToDTO(newDoctor), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorNurseDTO> updateDoctor(@RequestBody DoctorNurseDTO doctorDto) {
        Doctor newDoctor = this.convertToEntity(doctorDto);
        if (newDoctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Doctor modified = service.addDoctor(newDoctor);
        return new ResponseEntity<>(this.convertToDTO(modified), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") String id) {
        System.out.println("delete doctor " + id);

        Doctor doctor = service.findOne(id);
        if (doctor == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        System.out.println("delete this doctor = " + doctor);

        service.deleteById(id);
        return new ResponseEntity<>("doctor deleted", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorNurseDTO>> getAll() {
        System.out.println("get all doctor ");
        List<Doctor> doctorList = service.findAll();
        if (doctorList == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctorList.stream().map(this::convertToDTO).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/getDoctorForClinic/{id}")
    public ResponseEntity<List<DoctorNurseDTO>> getDoctorsForClinic(@PathVariable("id") String id) {
        System.out.println("get doctors for clinic " + id);

        List<Doctor> doctorList = service.getDoctorsForClinic(id);
        if (doctorList == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(doctorList);
        System.out.println("printing doctor clinic and user objects");
        for (Doctor d : doctorList) {
            System.out.println(d.getClinic());
            System.out.println(d.getUser());
            System.out.println(d.getSpecialties());
        }
        return new ResponseEntity<>(doctorList.stream().map(this::convertToDTO).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DoctorNurseDTO> getDoctor(@PathVariable("id") String id) {
        System.out.println("get doctor " + id);
        Doctor doctor = service.findOne(id);
        if (doctor == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println("get this doctor = " + doctor);

        return new ResponseEntity<>(this.convertToDTO(doctor), HttpStatus.OK);
    }

    private Doctor convertToEntity(DoctorNurseDTO doctorNurseDTO) {
        Doctor toAdd = new Doctor();
        Clinic foundClinic = clinicService.findOne(doctorNurseDTO.getClinicID());
        if(foundClinic == null) return null;

        toAdd.setClinic(foundClinic);
        User doctorUser = new User(
                doctorNurseDTO.getEmail(),
                passwordEncoder.encode(doctorNurseDTO.getPassword()),
                doctorNurseDTO.getName(),
                doctorNurseDTO.getSurname(),
                UserType.doctor,
                doctorNurseDTO.getPhoneNumber(),
                doctorNurseDTO.getAddress(),
                doctorNurseDTO.getCity(),
                doctorNurseDTO.getCountry(),
                doctorNurseDTO.getPersonalID());

        toAdd.setUser(doctorUser);

        List<InterventionType> interventionTypes = interventionTypeService.findMany(doctorNurseDTO.getSpecialties());
        if(interventionTypes == null) return null;
        toAdd.setSpecialties(interventionTypes);
        return toAdd;
    }

    private DoctorNurseDTO convertToDTO(Doctor doctor) {
        DoctorNurseDTO doctorNurseDTO = new DoctorNurseDTO(
                doctor.getId(),
                doctor.getUser().getEmail(),
                null,
                doctor.getUser().getName(),
                doctor.getUser().getSurname(),
                doctor.getUser().getPhoneNumber(),
                doctor.getUser().getAddress(),
                doctor.getUser().getCity(),
                doctor.getUser().getCountry(),
                doctor.getUser().getPersonalID(),
                doctor.getClinic().getId(),
                doctor.getSpecialties().stream().map(InterventionType::getId).collect(Collectors.toList())
        );
        return doctorNurseDTO;
    }
}

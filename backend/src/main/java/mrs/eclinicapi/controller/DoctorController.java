package mrs.eclinicapi.controller;

import lombok.AllArgsConstructor;
import mrs.eclinicapi.DTO.ClinicSearchRequest;
import mrs.eclinicapi.DTO.DoctorNurseDTO;
import mrs.eclinicapi.DTO.DoctorSearchRequest;
import mrs.eclinicapi.model.*;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import mrs.eclinicapi.service.InterventionTypeService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
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
    private UserService userService;

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

    @GetMapping(path = "/{pageNumber}/{pageSize}/{sort}/{desc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResponse> getAll(@PathVariable int pageNumber,
                                                       @PathVariable int pageSize,
                                                       @PathVariable String sort,
                                                       @PathVariable String desc) {
        PagedResponse response;
        if(pageSize < 1){
            List<Doctor> allDoctors = service.findAll();
            response = new PagedResponse(allDoctors.stream().map(this::convertToDTO).collect(Collectors.toList()),
                    allDoctors.size());

        } else {
            Page<Doctor> doctorPage;
            if(sort.equals("undefined"))
                doctorPage = service.findPaged(pageNumber, pageSize);
            else {
                doctorPage = service.findPaged(pageNumber, pageSize, sort, desc.equals("true"));
            }
            response = new PagedResponse(doctorPage.getContent().stream()
                    .map(this::convertToDTO).collect(Collectors.toList()), doctorPage.getTotalElements());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/clinic/{clinicID}/{pageNumber}/{pageSize}/{sort}/{desc}")
    public ResponseEntity<PagedResponse> getDoctorsForClinic(@PathVariable String clinicID,
                                                                    @PathVariable int pageNumber,
                                                                    @PathVariable int pageSize,
                                                                    @PathVariable String sort,
                                                                    @PathVariable String desc) {

        PagedResponse response;
        if(pageSize < 1){
            List<Doctor> allDoctors = service.findByClinicID(clinicID);
            response = new PagedResponse(allDoctors.stream().map(this::convertToDTO).collect(Collectors.toList()),
                    allDoctors.size());

        } else {
            Page<Doctor> doctorPage;
            if(sort.equals("undefined"))
                doctorPage = service.findByClinicIDPaged(clinicID, pageNumber, pageSize);
            else {
                sort = "user." + sort;
                doctorPage = service.findByClinicIDPaged(clinicID, pageNumber, pageSize, sort, desc.equals("true"));
            }
            response = new PagedResponse(doctorPage.getContent().stream()
                    .map(this::convertToDTO).collect(Collectors.toList()), doctorPage.getTotalElements());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
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
    @PostMapping(path = "/search/{pageNumber}/{pageSize}/{sort}/{desc}")
    public ResponseEntity<PagedResponse> searchDoctors(@RequestBody DoctorSearchRequest searchRequest,
                                                       @PathVariable int pageNumber,
                                                       @PathVariable int pageSize,
                                                       @PathVariable String sort,
                                                       @PathVariable String desc) {
        LocalDate date = searchRequest.getDate();
        InterventionType type = interventionTypeService.findOne(searchRequest.getInterventionTypeID());
        String searchQuery = searchRequest.getSearchQuery();
        if(searchQuery == null) searchQuery = "";
        if(type == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PagedResponse response;
        Page<Doctor> doctorPage;
        if(sort.equals("undefined"))
            doctorPage = service.search(searchQuery, searchRequest.getClinicID(), date, type, pageNumber, pageSize);
        else {
            sort = "user." + sort;
            doctorPage = service.search(searchQuery, searchRequest.getClinicID(), date, type, pageNumber, pageSize, sort, desc.equals("true"));
        }
        response = new PagedResponse(doctorPage.getContent()
                .stream().map(this::convertToDTO).collect(Collectors.toList()), doctorPage.getTotalElements());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Doctor convertToEntity(DoctorNurseDTO doctorNurseDTO) {
        Doctor toAdd = new Doctor();
        Clinic foundClinic = clinicService.findOne(doctorNurseDTO.getClinicID());
        if(foundClinic == null) return null;

        toAdd.setClinic(foundClinic);
        User foundUser = userService.findOne(doctorNurseDTO.getUserID());
        User doctorUser;
        if(foundUser == null)
            doctorUser = new User(
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
        else
            doctorUser = foundUser;

        toAdd.setUser(doctorUser);

        List<InterventionType> interventionTypes = interventionTypeService.findMany(doctorNurseDTO.getSpecialties());
        if(interventionTypes == null) return null;
        toAdd.setSpecialties(interventionTypes);
        return toAdd;
    }

    private DoctorNurseDTO convertToDTO(Doctor doctor) {
        DoctorNurseDTO doctorNurseDTO = new DoctorNurseDTO(
                doctor.getId(),
                doctor.getUser().getId(),
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
                doctor.getSpecialties().stream().map(InterventionType::getId).collect(Collectors.toList()),
                doctor.getWorkingCalendar().getWorkingSchedule(),
                doctor.getInterventions().stream().map(Intervention::getDateTime).collect(Collectors.toList())
        );
        return doctorNurseDTO;
    }


    @AllArgsConstructor
    static class PagedResponse {
        public List<DoctorNurseDTO> doctors;
        public long totalLength;
    }
}

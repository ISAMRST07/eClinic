package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.DoctorNurseDTO;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private DoctorService service;

    @Autowired
    private ClinicService clinicService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorNurseDTO doctorDto) {
        System.out.println("adding doctor ");
        System.out.println("doctordto = " + doctorDto);
        Doctor newDoctor = new Doctor();

        Clinic clinic = clinicService.findOne(doctorDto.getClinic());
        newDoctor.setClinic(clinic);
        User newUser = new User();
        newUser.setName(doctorDto.getName());
        newUser.setSurname(doctorDto.getSurname());
        newUser.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        newUser.setEmail(doctorDto.getEmail());
        newUser.setAddress(doctorDto.getAddress());
        newUser.setCity(doctorDto.getCity());
        newUser.setCountry(doctorDto.getCountry());
        newUser.setType(UserType.doctor);
        newUser.setPersonalID(doctorDto.getJmbg());
        newUser.setPhoneNumber(doctorDto.getPhone());

        newDoctor.setUser(newUser);
        newDoctor.setPosition(doctorDto.getPosition());

        service.addDoctor(newDoctor);
        //userService.addUser(newUser);

        System.out.println("newDoctor added = " + newDoctor);

        return new ResponseEntity<>(newDoctor, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> updateDoctor(@RequestBody DoctorNurseDTO doctorDto) {
        System.out.println("updateDoctor = " + doctorDto);
        Doctor oldDoctor = service.findOne(doctorDto.getId());
        User oldUser = oldDoctor.getUser();
        System.out.println("oldUser = " + oldUser);
        oldUser.setName(doctorDto.getName());
        oldUser.setSurname(doctorDto.getSurname());
        oldUser.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        oldUser.setEmail(doctorDto.getEmail());
        oldUser.setAddress(doctorDto.getAddress());
        oldUser.setCity(doctorDto.getCity());
        oldUser.setCountry(doctorDto.getCountry());
        oldUser.setPersonalID(doctorDto.getJmbg());
        oldUser.setPhoneNumber(doctorDto.getPhone());
        oldDoctor.setPosition(doctorDto.getPosition());
        System.out.println("updated = " + oldUser);

        System.out.println("new doctor oldDoctor = " + oldDoctor);

        Doctor modified = service.addDoctor(oldDoctor);
        System.out.println("new doctor modified = " + modified);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @RequestMapping(path = "/deleteDoctor/{id}")
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

    @RequestMapping(path = "/getAll")
    public ResponseEntity<List<Doctor>> getAll() {
        System.out.println("get all doctor ");
        List<Doctor> doctorList = service.findAll();
        if (doctorList == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctorList, HttpStatus.OK);
    }

    @RequestMapping(path = "/getDoctorForClinic/{id}")
    public ResponseEntity<List<Doctor>> getDoctorsForClinic(@PathVariable("id") String id) {
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
            System.out.println(d.getPosition());
        }
        return new ResponseEntity<>(doctorList, HttpStatus.OK);
    }

    @RequestMapping(path = "/getDoctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") String id) {
        System.out.println("get doctor " + id);
        Doctor doctor = service.findOne(id);
        if (doctor == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println("get this doctor = " + doctor);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}

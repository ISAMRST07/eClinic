package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.service.ClinicService;
import mrs.eclinicapi.service.DoctorService;
import mrs.eclinicapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ClinicService clinicService;

    @RequestMapping(path = "/addDoctor")
    public Doctor addDoctor(@RequestParam String position,
                            @RequestParam String userId,
                            @RequestParam String clinicId) {
        System.out.println("adding doctor " + position + " " + userId + " " + clinicId);
        User user = userService.findOne(userId);
        if (user == null) {
            System.out.println("user with id = " + userId + " == null");
        }
        System.out.println("found userId = " + userId + " = " + user);

        Clinic clinic = clinicService.findOne(clinicId);
        if (clinic == null) {
            System.out.println("clinic with id = " + clinicId + " == null");
        }
        System.out.println("found clinicId = " + clinicId + " = " + clinic);

        Doctor doctor = new Doctor();
        doctor.setPosition(position);
        doctor.setUser(user);
        doctor.setClinic(clinic);

        service.addDoctor(doctor);
        System.out.println("newDoctor = " + doctor);
        return doctor;
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

    @RequestMapping(path = "/updatePosition")
    public ResponseEntity<String> updatePosition(@RequestParam String id,
                                                 @RequestParam String newPosition) {
        System.out.println("get doctor " + id);
        Doctor doctor = service.findOne(id);
        if (doctor == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>("doctor not found", HttpStatus.NOT_FOUND);
        }
        System.out.println("update this doctor = " + doctor);
        doctor.setPosition(newPosition);
        service.addDoctor(doctor);
        return new ResponseEntity<>("positionUpdated", HttpStatus.OK);
    }

    @RequestMapping(path = "/deleteDoctorFromClinic")
    public ResponseEntity<String> deleteDoctorFromClinic(@RequestParam String id) {
        System.out.println("get doctor " + id);
        Doctor doctor = service.findOne(id);
        if (doctor == null) {
            System.out.println("doctor not found");
            return new ResponseEntity<>("doctor not found", HttpStatus.NOT_FOUND);
        }
        doctor.setClinic(null);
        System.out.println("update this doctor = " + doctor);
        service.addDoctor(doctor);
        return new ResponseEntity<>("doctor clinic removed", HttpStatus.OK);
    }
}

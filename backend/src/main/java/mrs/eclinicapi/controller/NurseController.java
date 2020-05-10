package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.DoctorNurseDTO;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.Nurse;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.enums.UserType;
import mrs.eclinicapi.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/nurse")
public class NurseController {

    @Autowired
    private NurseService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nurse> addNurse(@RequestBody DoctorNurseDTO nurseDto) {
        System.out.println("adding nurse ");
        System.out.println("nurseDto = " + nurseDto);
        Nurse newNurse = new Nurse();
        
        User newUser = new User();
        newUser.setName(nurseDto.getName());
        newUser.setSurname(nurseDto.getSurname());
        newUser.setPassword(nurseDto.getJmbg().toString());
        newUser.setEmail(nurseDto.getEmail());
        newUser.setAddress(nurseDto.getAddress());
        newUser.setCity(nurseDto.getCity());
        newUser.setCountry(nurseDto.getCountry());
        newUser.setType(UserType.nurse);
        newUser.setPersonalID(nurseDto.getJmbg());
        newUser.setPhoneNumber(nurseDto.getPhone());
       
        newNurse.setUser(newUser);
        newNurse.setPosition(nurseDto.getPosition());
        
        service.addNurse(newNurse);

        System.out.println("newDoctor added = " + newNurse);

        return new ResponseEntity<>(newNurse, HttpStatus.OK);
    }
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nurse> updateNurse(@RequestBody DoctorNurseDTO nurseDto) {
    	System.out.println("updateNurse = " + nurseDto);
    	
    	Nurse oldNurse = service.findOne(nurseDto.getId());
    	User oldUser = oldNurse.getUser();
    	
    	oldUser.setName(nurseDto.getName());
    	oldUser.setSurname(nurseDto.getSurname());
    	oldUser.setPassword(nurseDto.getJmbg().toString());
    	oldUser.setEmail(nurseDto.getEmail());
    	oldUser.setAddress(nurseDto.getAddress());
    	oldUser.setCity(nurseDto.getCity());
    	oldUser.setCountry(nurseDto.getCountry());
    	oldUser.setPersonalID(nurseDto.getJmbg());
    	oldUser.setPhoneNumber(nurseDto.getPhone());
    	oldNurse.setPosition(nurseDto.getPosition());

        System.out.println("new nurse oldNUrse = " + oldNurse);
        
        Nurse modified = service.addNurse(oldNurse);
        System.out.println("new nurse modified = " + modified);
        if (modified == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modified, HttpStatus.OK);
    }

    @RequestMapping(path = "/deleteNurse/{id}")
    public ResponseEntity<String> deleteNurse(@PathVariable("id") String id) {
        System.out.println("delete nurse " + id);

        Nurse nurse = service.findOne(id);
        if (nurse == null) {
            System.out.println("nurse not found");
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        }
        System.out.println("delete this nurse = " + nurse);

        service.deleteById(id);
        return new ResponseEntity<>("nurse deleted", HttpStatus.OK);
    }
    
    @RequestMapping(path = "/getAll")
    public ResponseEntity<List<Nurse>> getAll() {
        System.out.println("get all nurse ");
        List<Nurse> nurseList = service.findAll();
        if (nurseList == null) {
            System.out.println("nurse not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(nurseList, HttpStatus.OK);
    }

    @RequestMapping(path = "/getNurseForClinic/{id}")
    public ResponseEntity<List<Nurse>> getNurseForClinic(@PathVariable("id") String id) {
        System.out.println("get nurse for clinic " + id);

        List<Nurse> nurseList = service.getNurseForClinic(id);
        if(nurseList == null) {
            System.out.println("nurse not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(nurseList);
        return new ResponseEntity<>(nurseList, HttpStatus.OK);
    }
    @RequestMapping(path = "/getNurse/{id}")
    public ResponseEntity<Nurse> getNurse(@PathVariable("id") String id) {
        System.out.println("get nurse " + id);
        Nurse nurse = service.findOne(id);
        if (nurse == null) {
            System.out.println("nurse not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println("get this nurse = " + nurse);

        return new ResponseEntity<>(nurse, HttpStatus.OK);
    }

}

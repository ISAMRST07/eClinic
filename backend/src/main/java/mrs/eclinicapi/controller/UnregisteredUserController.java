package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.UnregisteredUser;
import mrs.eclinicapi.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/unregisteredusers")
public class UnregisteredUserController {

    @Autowired
    private UnregisteredUserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnregisteredUser>> getUUsers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnregisteredUser> sendEmail() {
        System.out.println("Will send email");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

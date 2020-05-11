package mrs.eclinicapi.controller;

import mrs.eclinicapi.DTO.OnRegistrationCompleteEvent;
import mrs.eclinicapi.model.UnregisteredUser;
import mrs.eclinicapi.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/unregisteredusers")
public class UnregisteredUserController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private UnregisteredUserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnregisteredUser>> getUUsers() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnregisteredUser> sendEmail(@PathVariable String id, HttpServletRequest request) {
        UnregisteredUser unregisteredUser = service.findById(id);
        if (unregisteredUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(unregisteredUser,
                request.getLocale(), appUrl));
        unregisteredUser.setEmailSent(true);
        service.save(unregisteredUser);
        return new ResponseEntity<>(unregisteredUser, HttpStatus.OK);
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteRequest(@PathVariable String id) {
        UnregisteredUser found = service.findById(id);
        if (found == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

}

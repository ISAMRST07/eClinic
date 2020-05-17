package mrs.eclinicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.model.VacationRequest;
import mrs.eclinicapi.service.VacationRequestService;


@RestController
@RequestMapping(value = "api/vacationRequest")
public class VacationRequestController {

	@Autowired
    private VacationRequestService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VacationRequest>> getAllVacationRequest() {
        List<VacationRequest> vacationRequest = service.findAll();
        if (vacationRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (VacationRequest v : vacationRequest) {
            System.out.println("getall vacationRequest = " + v);
        }
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/{id}")
    public ResponseEntity<List<VacationRequest>> getVacationRequestForClinic(@PathVariable("id") String id) {
        System.out.println("get vacationRequest for clinic " + id);

        List<VacationRequest> vacationRequest = service.getVacationRequestForClinic(id);
        if (vacationRequest == null) {
            System.out.println("vacationRequest not found");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        System.out.println(vacationRequest);
        System.out.println("printing vacationRequest clinic");
        for (VacationRequest d : vacationRequest) {
            System.out.println(d);
        }
        return new ResponseEntity<>(vacationRequest, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VacationRequest> addVacationRequest(@RequestBody VacationRequest vacationRequest) {
		System.out.println("addVacationRequest = " + vacationRequest);
		VacationRequest newVacation = service.addVacationRequest(vacationRequest);
        if (newVacation == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        System.out.println("newVacation = " + newVacation);
        return new ResponseEntity<>(newVacation, HttpStatus.OK);
    }
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VacationRequest> modifyVacationRequest(@RequestBody VacationRequest vacationRequest) {
		VacationRequest modified = service.modifyVacationRequest(vacationRequest);
	    if (modified == null) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(modified, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVacationRequest(@PathVariable("id") String id) {
		VacationRequest vacationRequest = service.findOne(id);
        if (vacationRequest == null) {
            return new ResponseEntity<>("vacationRequest not found", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return new ResponseEntity<>("deleted vacationRequest", HttpStatus.OK);
    }

}

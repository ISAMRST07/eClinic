package mrs.eclinicapi.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mrs.eclinicapi.model.Test;
import mrs.eclinicapi.service.TestService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/test")
public class TestController {

	@Autowired
	private TestService testService;
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Test>> getTests() {
		Collection<Test> greetings = testService.findTests();
		return new ResponseEntity<Collection<Test>>(greetings, HttpStatus.OK);
	}
}

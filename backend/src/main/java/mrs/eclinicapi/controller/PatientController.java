package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.PatientService;

@RestController
@RequestMapping(value = "api/patient")
public class PatientController {

	@Autowired
	private PatientService service;

}

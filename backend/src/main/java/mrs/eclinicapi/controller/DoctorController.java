package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.DoctorService;

@RestController
@RequestMapping(value = "api/doctor")
public class DoctorController {

	@Autowired
	private DoctorService service;
    
}

package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.NurseService;

@RestController
@RequestMapping(value = "api/nurse")
public class NurseController {

	@Autowired
	private NurseService service;
	
}

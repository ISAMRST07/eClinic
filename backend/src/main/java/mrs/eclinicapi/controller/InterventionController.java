package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.InterventionService;

@RestController
@RequestMapping(value = "api/intervention")
public class InterventionController {

	@Autowired
	private InterventionService service;

}

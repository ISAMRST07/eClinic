package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.PrescriptionService;

@RestController
@RequestMapping(value = "api/prescription")
public class PrescriptionController {

	@Autowired
	private PrescriptionService service;
	
}

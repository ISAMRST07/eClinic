package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.MedicalStaffService;

@RestController
@RequestMapping(value = "api/medicalstaff")
public class MedicalStaffController {

	@Autowired
	private MedicalStaffService service;

}

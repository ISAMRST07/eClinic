package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.MedicalRecordService;

@RestController
@RequestMapping(value = "api/medicalrecord")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService service;
}

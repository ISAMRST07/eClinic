package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.ClinicalCenterAdministratorService;

@RestController
@RequestMapping(value = "api/clinicalcenteradministrator")
public class ClinicalCenterAdministratorController {

	@Autowired
	private ClinicalCenterAdministratorService service;

}

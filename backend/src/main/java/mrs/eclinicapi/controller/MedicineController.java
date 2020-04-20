package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.MedicineService;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {

	@Autowired
	private MedicineService service;

}

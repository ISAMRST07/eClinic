package mrs.eclinicapi.controller;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/clinic")
public class ClinicController {

	private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @PostMapping()
    public Clinic addClinic(@RequestParam String name, @RequestParam String id) {
    	Clinic newClinic = new Clinic();
    	newClinic.setName(name);
    	clinicService.addClinic(newClinic);
    	System.out.println("newClinic = " + newClinic);
        return newClinic;
    }
    
}

package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {

    @Autowired
    private MedicineService service;

}

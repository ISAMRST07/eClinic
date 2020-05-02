package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/intervention")
public class InterventionController {

    @Autowired
    private InterventionService service;

}

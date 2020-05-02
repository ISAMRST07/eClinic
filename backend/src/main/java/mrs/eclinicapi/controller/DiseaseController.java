package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService service;

}

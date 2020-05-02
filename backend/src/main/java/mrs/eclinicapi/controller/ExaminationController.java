package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/examination")
public class ExaminationController {

    @Autowired
    private ExaminationService service;

}

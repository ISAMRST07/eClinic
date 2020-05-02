package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

}

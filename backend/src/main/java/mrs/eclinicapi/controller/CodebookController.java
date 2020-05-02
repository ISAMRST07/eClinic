package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/codebook")
public class CodebookController {

    @Autowired
    private CodebookService service;

}

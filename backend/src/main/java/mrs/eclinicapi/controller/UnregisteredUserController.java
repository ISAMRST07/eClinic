package mrs.eclinicapi.controller;

import mrs.eclinicapi.service.UnregisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/unregistereduser")
public class UnregisteredUserController {

    @Autowired
    private UnregisteredUserService service;

}

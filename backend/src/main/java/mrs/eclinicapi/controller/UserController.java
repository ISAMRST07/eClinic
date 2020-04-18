package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.UserService;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

	@Autowired
	private UserService service;

}

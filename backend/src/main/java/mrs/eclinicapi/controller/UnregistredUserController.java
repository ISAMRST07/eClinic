package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.UnregistredUserService;

@RestController
@RequestMapping(value = "api/unregistreduser")
public class UnregistredUserController {

	@Autowired
	private UnregistredUserService service;

}

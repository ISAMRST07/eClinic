package mrs.eclinicapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mrs.eclinicapi.service.CodebookService;

@RestController
@RequestMapping(value = "api/codebook")
public class CodebookController {

	@Autowired
	private CodebookService service;

}

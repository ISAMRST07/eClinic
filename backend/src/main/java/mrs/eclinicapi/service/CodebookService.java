package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.CodebookRepository;

@Service
public class CodebookService {

	private CodebookRepository repository;
	
	@Autowired
	public CodebookService(CodebookRepository repository) {
		this.repository = repository;
	}

}

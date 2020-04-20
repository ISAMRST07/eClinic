package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.ClinicalCentarAdministratorRepository;

@Service
public class ClinicalCenterAdministratorService {

	private ClinicalCentarAdministratorRepository repository;
	
	@Autowired
	public ClinicalCenterAdministratorService(ClinicalCentarAdministratorRepository repository) {
		this.repository = repository;
	}

}

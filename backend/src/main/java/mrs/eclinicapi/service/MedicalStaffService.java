package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.MedicalStaffRepository;

@Service
public class MedicalStaffService {

	private MedicalStaffRepository repository;
	
	@Autowired
	public MedicalStaffService(MedicalStaffRepository repository) {
		this.repository = repository;
	}

}

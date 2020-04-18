package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	private MedicalRecordRepository repository;
	
	@Autowired
	public MedicalRecordService(MedicalRecordRepository repository) {
		this.repository = repository;
	}

}

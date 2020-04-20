package mrs.eclinicapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mrs.eclinicapi.repository.MedicineRepository;

@Service
public class MedicineService {

	private MedicineRepository repository;
	
	@Autowired
	public MedicineService(MedicineRepository repository) {
		this.repository = repository;
	}

}

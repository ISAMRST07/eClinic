package mrs.eclinicapi.service;

import mrs.eclinicapi.DTO.ClinicRoomDTO;
import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.Doctor;
import mrs.eclinicapi.repository.ClinicRoomRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClinicRoomService {

	@Autowired
	private ClinicRoomRepository repository;

	private ClinicService clinicService;

	public ClinicRoom addClinicRoom(ClinicRoom newClinicRoom){
		return repository.save(newClinicRoom);
	}

	public ClinicRoom findOne(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public List<ClinicRoom> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}

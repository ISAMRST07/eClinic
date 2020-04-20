package mrs.eclinicapi.service;

import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.repository.ClinicRoomRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicRoomService {
 
	private ClinicRoomRepository repository;
	
	@Autowired
	public ClinicRoomService(ClinicRoomRepository repository) {
		this.repository = repository;
	}
}

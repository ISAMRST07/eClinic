package mrs.eclinicapi.service;

import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.repository.ClinicRoomRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicRoomService {
 
	@Autowired
	private ClinicRoomRepository repository;
	
	public ClinicRoom addClinicRoom(ClinicRoom newClinicRoom){
		return repository.save(newClinicRoom);
	}
}

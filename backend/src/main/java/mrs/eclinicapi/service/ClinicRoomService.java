package mrs.eclinicapi.service;

import mrs.eclinicapi.model.ClinicRoom;

import java.util.Collection;
import java.util.List;

public interface ClinicRoomService {
    List<ClinicRoom> getAllClinicRooms();
	void remove(int id);
	ClinicRoom findOneClinicRoom(int id);
}

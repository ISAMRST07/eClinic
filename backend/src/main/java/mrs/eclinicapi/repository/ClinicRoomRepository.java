package mrs.eclinicapi.repository;

import java.util.List;

import mrs.eclinicapi.model.ClinicRoom;

public interface ClinicRoomRepository {
	List<ClinicRoom> getAllClinicRooms();

	void deleteById(int id);

	ClinicRoom findById(int id);
}

package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.ClinicRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryClinicRoomRepository implements ClinicRoomRepository {
    @Override
    public List<ClinicRoom> getAllClinicRooms() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public ClinicRoom findById(int id) {
        return null;
    }
}

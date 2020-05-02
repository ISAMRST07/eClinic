package mrs.eclinicapi.service;

import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.repository.ClinicRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicRoomService {

    @Autowired
    private ClinicRoomRepository repository;

    private ClinicService clinicService;

    public ClinicRoom addClinicRoom(ClinicRoom newClinicRoom) {
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

    public ClinicRoom modifyRoom(ClinicRoom clinicRoom) {
        if (this.findOne(clinicRoom.getId()) == null) return null;
        return repository.save(clinicRoom);
    }
}

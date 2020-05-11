package mrs.eclinicapi.service;

import mrs.eclinicapi.model.ClinicAdministrator;
import mrs.eclinicapi.model.ClinicRoom;
import mrs.eclinicapi.model.User;
import mrs.eclinicapi.repository.ClinicAdministratorRepository;
import mrs.eclinicapi.repository.ClinicRoomRepository;
import mrs.eclinicapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicRoomService {

    @Autowired
    private ClinicRoomRepository repository;
    @Autowired
    private ClinicAdministratorRepository clinicAdministratorRepository;
    @Autowired
    private UserRepository userRepository;

    private ClinicService clinicService;

    public ClinicRoom addClinicRoom(ClinicRoom newClinicRoom) {
        return repository.save(newClinicRoom);
    }

    public ClinicRoom findOne(String id) {
        return repository.findById(id).orElseGet(null);
    }

    public List<ClinicRoom> findAll() {
        return repository.findAll();
    }

    public List<ClinicRoom> findOneClinicRooms(String clinicAdminID) {
        List<ClinicRoom> clinicRooms = repository.findAll();
        List<ClinicRoom> oneClinicRooms = new ArrayList<>();
        Optional<User> user = userRepository.findById(clinicAdminID);
        if (!user.isPresent()) {
            return oneClinicRooms;
        }
        Optional<ClinicAdministrator> cAdmin = clinicAdministratorRepository.findClinicAdministratorByUser(user.get());
        System.out.println("ADMINEEEE");
        System.out.println(clinicAdminID);
        System.out.println(cAdmin.isPresent());
        if (!cAdmin.isPresent()) {
            return oneClinicRooms;
        }
        for (ClinicRoom cRoom : clinicRooms) {
            if (cAdmin.get().getClinic().getId().equals(cRoom.getClinic().getId())) {
                oneClinicRooms.add(cRoom);
            }
        }
        return oneClinicRooms;
    }

    ;

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public ClinicRoom modifyRoom(ClinicRoom clinicRoom) {
        if (this.findOne(clinicRoom.getId()) == null) return null;
        return repository.save(clinicRoom);
    }
}

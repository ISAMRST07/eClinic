package mrs.eclinicapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.ClinicRoom;

public interface ClinicRoomRepository extends JpaRepository<ClinicRoom, Long>{

}

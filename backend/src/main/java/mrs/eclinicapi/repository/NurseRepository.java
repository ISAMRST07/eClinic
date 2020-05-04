package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, String> {

}

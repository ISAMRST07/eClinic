package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.ClinicAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicAdministratorRepository extends JpaRepository<ClinicAdministrator, String> {
//    ClinicAdministrator findByUsername(String username);

}

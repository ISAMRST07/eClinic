package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.UnregisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnregisteredUserRepository extends JpaRepository<UnregisteredUser, Long> {

}
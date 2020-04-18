package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

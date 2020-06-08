package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.User;
import mrs.eclinicapi.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, String> {

}

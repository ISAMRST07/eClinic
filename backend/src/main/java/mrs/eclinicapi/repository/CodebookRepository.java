package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodebookRepository extends JpaRepository<Codebook, String> {

}

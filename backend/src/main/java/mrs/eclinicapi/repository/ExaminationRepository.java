package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {

}

package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Examination;

public interface ExaminationRepository extends JpaRepository<Examination, Long>{

}

package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long>{

}

package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import mrs.eclinicapi.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, Long>{

}

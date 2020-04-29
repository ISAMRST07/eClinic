package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.Clinic;
import mrs.eclinicapi.model.ClinicAdministrator;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}


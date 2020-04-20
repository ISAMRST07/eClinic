package mrs.eclinicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mrs.eclinicapi.model.UnregistredUser;

public interface UnregistredUserRepository extends JpaRepository<UnregistredUser, Long>{

}

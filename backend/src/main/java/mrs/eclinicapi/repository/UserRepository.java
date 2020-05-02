package mrs.eclinicapi.repository;

import mrs.eclinicapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User SET username = ?2 WHERE id = ?1")
    void updateUsername(Long id, String newUsername);

    @Modifying
    @Query("UPDATE User SET password = ?2 WHERE id = ?1")
    void updatePassword(Long id, String newPassword);

    @Modifying
    @Query("UPDATE User SET name = ?2 WHERE id = ?1")
    void updateName(Long id, String newName);

}

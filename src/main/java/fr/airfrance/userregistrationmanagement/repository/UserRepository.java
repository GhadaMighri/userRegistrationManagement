package fr.airfrance.userregistrationmanagement.repository;

import fr.airfrance.userregistrationmanagement.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where u.email= :email")
  Optional<User> findUserByEmail(String email);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}

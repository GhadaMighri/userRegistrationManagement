package fr.airfrance.userregistrationmanagement.service.interfaces;

import fr.airfrance.userregistrationmanagement.entity.User;
import java.util.Optional;

public interface IUserRegistrationService {

  User registerUser(User user);

  boolean checkIfUserExistsByEmail(String email);

  boolean checkIfUserExistsByUsername(String username);

  Optional<User> getUserByEmail(String email);
}

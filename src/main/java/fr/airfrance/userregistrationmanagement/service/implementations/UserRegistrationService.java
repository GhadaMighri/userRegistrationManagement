package fr.airfrance.userregistrationmanagement.service.implementations;

import fr.airfrance.userregistrationmanagement.entity.Role;
import fr.airfrance.userregistrationmanagement.entity.User;
import fr.airfrance.userregistrationmanagement.exception.custom.ResourceNotValidException;
import fr.airfrance.userregistrationmanagement.repository.RoleRepository;
import fr.airfrance.userregistrationmanagement.repository.UserRepository;
import fr.airfrance.userregistrationmanagement.service.interfaces.IUserRegistrationService;
import java.util.Collections;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService implements
    IUserRegistrationService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  public UserRegistrationService(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * this method allow us to register a user
   *
   * @param user
   * @return user
   */
  @Override
  public User registerUser(User user) {
    if (checkIfUserExistsByUsername(user.getName())) {
      throw new ResourceNotValidException();
    }
    if (checkIfUserExistsByEmail(user.getEmail())) {
      throw new ResourceNotValidException();
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Role roles = roleRepository.findByName("ROLE_ADMIN").get();
    user.setRoles(Collections.singleton(roles));
    return userRepository.save(user);
  }

  /**
   * this method allow us to check whether a user exists by email or not
   *
   * @param email
   * @return boolean
   */
  @Override
  public boolean checkIfUserExistsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  /**
   * this method allow us to check whether a user exists by username or not
   *
   * @param username
   * @return boolean
   */
  @Override
  public boolean checkIfUserExistsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  /**
   * This method allows us to display a user
   *
   * @param email
   * @return Optional<User>
   */
  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }
}

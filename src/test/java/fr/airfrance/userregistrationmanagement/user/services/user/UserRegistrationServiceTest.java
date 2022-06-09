package fr.airfrance.userregistrationmanagement.user.services.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import fr.airfrance.userregistrationmanagement.entity.User;
import fr.airfrance.userregistrationmanagement.repository.RoleRepository;
import fr.airfrance.userregistrationmanagement.repository.UserRepository;
import fr.airfrance.userregistrationmanagement.service.implementations.UserRegistrationService;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRegistrationServiceTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private PasswordEncoder passwordEncoder;

  private UserRegistrationService userRegistrationService;

  private User user;

  @Before
  public void setUp() {
    user.setId(1L);
    user.setCountry("France");
    user.setEmail("test@gmail.com");
    user.setUsername("test");
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void registerUser() {
    when(userRepository.save(any())).thenReturn(user);

    User result = userRegistrationService.registerUser(user);
    assertEquals(result.getId(), user.getId());
  }

  @Test
  public void getUserByEmail() {
    when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(user));
    Optional<User> result = userRegistrationService.getUserByEmail("test@gmail.com");
    assertEquals(result, Optional.of(user));
  }
}

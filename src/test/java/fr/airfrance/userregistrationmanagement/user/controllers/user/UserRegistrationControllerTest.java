package fr.airfrance.userregistrationmanagement.user.controllers.user;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.airfrance.userregistrationmanagement.controller.UserRegistrationController;
import fr.airfrance.userregistrationmanagement.dto.UserDto;
import fr.airfrance.userregistrationmanagement.entity.User;
import fr.airfrance.userregistrationmanagement.repository.RoleRepository;
import fr.airfrance.userregistrationmanagement.repository.UserRepository;
import fr.airfrance.userregistrationmanagement.service.implementations.UserRegistrationService;
import fr.airfrance.userregistrationmanagement.util.ObjectMapper;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationControllerTest {

  @Mock
  private UserRegistrationService userRegistrationService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @InjectMocks
  private UserRegistrationController userRegistrationController;

  private MockMvc mockMvc;
  private User user;

  @Before
  public void SetUp() throws Exception {
    user.setId(1L);
    user.setCountry("France");
    user.setEmail("test@gmail.com");
    user.setUsername("test");

    userRegistrationService = new UserRegistrationService(userRepository,roleRepository,passwordEncoder);

    MockitoAnnotations.initMocks(this);
    /*
     * Une méthode permettant de builder une instance de MockMvc en mode Standalone
     * et enregistrer un ou plusieurs contrôleurs de l’application dans le web
     * context par programmation.
     */
    mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();
  }

  /**
   *
   * @throws Exception
   */
  @Test
  public void registerUser() throws Exception {
    when(userRegistrationService.registerUser(any())).thenReturn(user);

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.post("/userRegistration/register")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
  }

  /**
   *
   * @throws Exception
   */
  @Test
  public void getUserByEmail() throws Exception {
    when(userRegistrationService.getUserByEmail(any())).thenReturn(Optional.of(user));

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.post("/userRegistration/userByEmail/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
  }
}

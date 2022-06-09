package fr.airfrance.userregistrationmanagement.controller;


import fr.airfrance.userregistrationmanagement.dto.UserDto;
import fr.airfrance.userregistrationmanagement.entity.User;
import fr.airfrance.userregistrationmanagement.exception.custom.ResourceNotFoundException;
import fr.airfrance.userregistrationmanagement.service.implementations.UserRegistrationService;
import fr.airfrance.userregistrationmanagement.util.ObjectMapper;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRegistration")
public class UserRegistrationController {

  private UserRegistrationService userService;

  public UserRegistrationController(UserRegistrationService userService) {
    this.userService = userService;
  }

  /**
   * This method allows us to register a user
   *
   * @param userDto
   * @return ResponseEntity
   */
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
    ObjectMapper
        .map(userService.registerUser(ObjectMapper.map(userDto, User.class)), UserDto.class);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  /**
   * This method allows us to display a user
   *
   * @param email
   * @return user
   */
  @GetMapping("/userByEmail/{email}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto getUserByEmail(@PathVariable("email") String email) {
    if (!userService.checkIfUserExistsByEmail(email)) {
      throw new ResourceNotFoundException();
    } else {
      return ObjectMapper.map(userService.getUserByEmail(email), UserDto.class);
    }
  }
}

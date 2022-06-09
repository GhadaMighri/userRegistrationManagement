package fr.airfrance.userregistrationmanagement.security;

import fr.airfrance.userregistrationmanagement.entity.Role;
import fr.airfrance.userregistrationmanagement.entity.User;
import fr.airfrance.userregistrationmanagement.repository.UserRepository;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class RegistredUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public RegistredUserDetailsService(
      UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with username or email:" + username));
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), mapRolesToAuthorities((Set<Role>) user.getRoles()));
  }

  private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(
        Collectors.toList());
  }
}

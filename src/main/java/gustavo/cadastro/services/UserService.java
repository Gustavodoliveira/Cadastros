package gustavo.cadastro.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gustavo.cadastro.Dtos.Users.LoginUserDto;
import gustavo.cadastro.Dtos.Users.RegisterUserDto;
import gustavo.cadastro.infra.security.TokenService;
import gustavo.cadastro.models.User;
import gustavo.cadastro.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private AuthenticationManager authenticationManager;

  public User postUser(RegisterUserDto data) {

    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data, encryptedPassword);
    repository.save(newUser);
    return newUser;

  }

  public Authentication LoginUser(LoginUserDto data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);

    return auth;
  }

  public List<User> getUsers() {
    return repository.findAll();
  }
}
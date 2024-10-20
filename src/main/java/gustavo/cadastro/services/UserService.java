package gustavo.cadastro.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gustavo.cadastro.Dtos.Users.LoginUserDto;
import gustavo.cadastro.Dtos.Users.RegisterUserDto;
import gustavo.cadastro.Dtos.Users.UpdateUserDto;
import gustavo.cadastro.infra.security.FilterSecurity;
import gustavo.cadastro.infra.security.TokenService;
import gustavo.cadastro.models.User;
import gustavo.cadastro.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {
  @Autowired
  private TokenService tokenService;

  @Autowired
  HttpServletRequest req;

  @Autowired
  FilterSecurity filterSecurity;

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

  public User updateUser(UpdateUserDto data) {
    String token = filterSecurity.recoverToken(this.req);
    String userName = tokenService.validateToken(token);
    User user = repository.findByEmail(userName);
    User userUpdate = new User();

    userUpdate.setRole(user.getRole());

    if (data.email().isEmpty())
      userUpdate.setEmail(user.getEmail());
    else
      userUpdate.setEmail(data.email());

    if (data.userName() == null)
      userUpdate.setUserName(user.getUsername());
    else
      userUpdate.setUserName(data.userName());

    if (data.newPassword().isEmpty())
      userUpdate.setPassword(user.getPassword());
    else
      userUpdate.setPassword(data.newPassword());

    repository.updateUser(userUpdate.getEmail(), userUpdate.getUsername(), userUpdate.getPassword(),
        userName);

    return userUpdate;
  }

  public String DeleteUser() {
    String token = filterSecurity.recoverToken(this.req);
    String userName = tokenService.validateToken(token);
    User user = repository.findByEmail(userName);
    repository.deleteById(user.getId());

    return "Delete success";
  }
};
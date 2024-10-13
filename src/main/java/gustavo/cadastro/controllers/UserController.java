package gustavo.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gustavo.cadastro.Dtos.Users.LoginResponseDto;
import gustavo.cadastro.Dtos.Users.LoginUserDto;
import gustavo.cadastro.Dtos.Users.RegisterUserDto;
import gustavo.cadastro.infra.security.TokenService;
import gustavo.cadastro.models.User;
import gustavo.cadastro.repository.UserRepository;

@RestController()
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserRepository repository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  private ResponseEntity postUser(@RequestBody @Validated RegisterUserDto data) {
    String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
    User newUser = new User(data, encryptedPassword);
    repository.save(newUser);

    return ResponseEntity.ok().body(newUser);
  }

  @PostMapping("/login")
  private ResponseEntity loginUser(@RequestBody @Validated LoginUserDto data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDto(token));
  }
}

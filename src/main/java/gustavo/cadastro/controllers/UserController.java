package gustavo.cadastro.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gustavo.cadastro.Dtos.Users.LoginResponseDto;
import gustavo.cadastro.Dtos.Users.LoginUserDto;
import gustavo.cadastro.Dtos.Users.RegisterUserDto;
import gustavo.cadastro.infra.security.TokenService;
import gustavo.cadastro.models.User;
import gustavo.cadastro.services.UserService;

@RestController()
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/register")
  private ResponseEntity postUser(@RequestBody @Validated RegisterUserDto data) {
    User newUser = userService.postUser(data);
    var token = tokenService.generateToken(newUser);
    return ResponseEntity.ok().body(token);
  }

  @PostMapping("/login")
  private ResponseEntity loginUser(@RequestBody @Validated LoginUserDto data) {
    var auth = userService.LoginUser(data);
    var token = tokenService.generateToken((User) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDto(token));
  }

  @GetMapping
  private ResponseEntity getUsers() {
    List<User> users = userService.getUsers();
    return ResponseEntity.ok().body(users);
  }
}

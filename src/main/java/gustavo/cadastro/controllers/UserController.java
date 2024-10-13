package gustavo.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gustavo.cadastro.Dtos.UserDto;
import gustavo.cadastro.models.User;
import gustavo.cadastro.repository.UserRepository;

@RestController()
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserRepository repository;

  @PostMapping
  private ResponseEntity postUser(@RequestBody UserDto data) {

    User newUser = new User(data);
    repository.save(newUser);
    return ResponseEntity.ok().body(newUser);

  }
}

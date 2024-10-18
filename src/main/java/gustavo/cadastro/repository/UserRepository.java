package gustavo.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import gustavo.cadastro.models.User;

public interface UserRepository extends JpaRepository<User, String> {
  User findByEmail(String email);

  UserDetails findByUserName(String username);
}

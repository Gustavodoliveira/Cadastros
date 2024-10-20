package gustavo.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import gustavo.cadastro.Dtos.Users.UpdateUserDto;
import gustavo.cadastro.models.User;
import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
  User findByEmail(String email);

  UserDetails findByUserName(String username);

  @Transactional
  @Modifying
  @Query("update User u set u.email = ?1, u.userName = ?2, u.password = ?3 where u.email = ?4")
  Integer updateUser(String email, String userName, String password, String beforeEmail);

}

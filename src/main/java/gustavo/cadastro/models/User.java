package gustavo.cadastro.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import gustavo.cadastro.Dtos.Users.RegisterUserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(length = 60, nullable = false, unique = true)
  private String userName;

  @Column(length = 30, nullable = false, unique = true)
  private String email;

  @JoinColumn(name = "department_id")
  @OneToOne
  private Department department;

  @Column(length = 150, nullable = false)
  private String password;

  @Column()
  private Roles role;

  public User(RegisterUserDto data) {
    this.email = data.email();
    this.userName = data.userName();
    this.password = data.password();
    this.role = data.role();
  }

  public User(RegisterUserDto data, String encryptedPassword) {
    this.email = data.email();
    this.userName = data.userName();
    this.password = encryptedPassword;
    this.role = data.role();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == Roles.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.userName;
  }
}

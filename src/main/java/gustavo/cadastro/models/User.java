package gustavo.cadastro.models;

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
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(length = 60, nullable = false)
  private String userName;

  @Column(length = 30, nullable = false)
  private String email;

  @JoinColumn(name = "department_id")
  @OneToOne
  private Department department;

  @Column(length = 150, nullable = false)
  private String password;
}

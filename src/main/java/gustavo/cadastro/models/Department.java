package gustavo.cadastro.models;

import gustavo.cadastro.Dtos.Department.DepartmentRegisterDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Department {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 35, nullable = false)
  private String department;

  @Column(nullable = false)
  private Float salary;

  public Department(DepartmentRegisterDto data) {
    this.department = data.department();
    this.salary = data.salary();
  }
}

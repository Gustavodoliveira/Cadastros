package gustavo.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import gustavo.cadastro.models.Department;
import jakarta.transaction.Transactional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
  @Transactional
  @Modifying
  @Query("update Department d set d.department = ?1, d.salary=?2 where d.id = ?3")
  Integer updateDepartment(String department, Float salary, Integer id);
}

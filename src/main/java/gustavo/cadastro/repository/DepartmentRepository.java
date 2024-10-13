package gustavo.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gustavo.cadastro.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}

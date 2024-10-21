package gustavo.cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import gustavo.cadastro.Dtos.Department.UpdateDepartmentDto;
import gustavo.cadastro.models.Department;
import gustavo.cadastro.repository.DepartmentRepository;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepository repository;

  public List<Department> getDepartment() {
    return repository.findAll();
  }

  public String updateDepartment(UpdateDepartmentDto data, Integer id) {
    repository.updateDepartment(data.department(), data.salary(), id);
    return "update department success";
  }
}

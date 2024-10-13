package gustavo.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gustavo.cadastro.Dtos.DepartmentDto;
import gustavo.cadastro.models.Department;
import gustavo.cadastro.repository.DepartmentRepository;

@RestController()
@RequestMapping("/department")
public class DepartmentController {
  @Autowired
  private DepartmentRepository repository;

  @PostMapping
  private ResponseEntity postDepartment(@RequestBody DepartmentDto data) {
    Department newDepartment = new Department(data);
    repository.save(newDepartment);
    return ResponseEntity.ok().body(newDepartment);
  }

}

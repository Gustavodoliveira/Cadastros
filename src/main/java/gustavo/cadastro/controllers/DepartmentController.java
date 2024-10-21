package gustavo.cadastro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gustavo.cadastro.Dtos.Department.DepartmentRegisterDto;
import gustavo.cadastro.Dtos.Department.UpdateDepartmentDto;
import gustavo.cadastro.models.Department;
import gustavo.cadastro.repository.DepartmentRepository;
import gustavo.cadastro.services.DepartmentService;

@RestController()
@RequestMapping("/department")
public class DepartmentController {
  @Autowired
  private DepartmentRepository repository;

  @Autowired
  private DepartmentService service;

  @PostMapping
  private ResponseEntity postDepartment(@RequestBody DepartmentRegisterDto data) {
    Department newDepartment = new Department(data);
    repository.save(newDepartment);
    return ResponseEntity.ok().body(newDepartment);
  }

  @GetMapping
  private ResponseEntity getDepartments() {
    List<Department> dep = service.getDepartment();
    return ResponseEntity.ok().body(dep);
  }

  @PatchMapping("/{id}")
  private ResponseEntity updateDepartment(@PathVariable Integer id, @RequestBody @Validated UpdateDepartmentDto data) {
    String resp = service.updateDepartment(data, id);
    return ResponseEntity.ok(resp);
  }

}

package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.model.Employee;
import com.clinica.clinicaveterinaria.service.EmployeeService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<String> createUser(@Valid @RequestBody final Employee employee,
      BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error: Los datos del usuario no son válidos.");
    }
    try {
      Employee createdEmployee = employeeService.createUser(employee);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Usuario creado exitosamente con ID: " + createdEmployee.getId());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el usuario.");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable final int id) {
    try {
      Employee employee = employeeService.getUserById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Usuario no encontrado."));
      return ResponseEntity.ok(employee);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el usuario.");
    }
  }

  @GetMapping
  public ResponseEntity<List<Employee>> getAllUsers() {
    List<Employee> employees = employeeService.getAllUsers();
    return ResponseEntity.ok(employees);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable final int id,
      @RequestBody @Valid final Employee employeeDetails, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error: Los datos del usuario no son válidos.");
    }
    try {
      Employee updatedEmployee = employeeService.updateUser(id, employeeDetails);
      return ResponseEntity.ok("Usuario actualizado exitosamente.");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el usuario.");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable final int id) {
    try {
      employeeService.deleteUser(id);
      return ResponseEntity.ok("Usuario eliminado exitosamente.");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el usuario.");
    }
  }

}

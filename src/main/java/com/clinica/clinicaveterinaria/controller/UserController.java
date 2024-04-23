package com.clinica.clinicaveterinaria.controller;

import com.clinica.clinicaveterinaria.model.User;
import com.clinica.clinicaveterinaria.service.UserService;
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
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public ResponseEntity<String> createUser(@Valid @RequestBody final User user,
      BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error: Los datos del usuario no son válidos.");
    }
    try {
      User createdUser = userService.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body("Usuario creado exitosamente con ID: " + createdUser.getId());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el usuario.");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable final int id) {
    try {
      User user = userService.getUserById(id)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
              "Usuario no encontrado."));
      return ResponseEntity.ok(user);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el usuario.");
    }
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateUser(@PathVariable final int id,
      @RequestBody @Valid final User userDetails, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error: Los datos del usuario no son válidos.");
    }
    try {
      User updatedUser = userService.updateUser(id, userDetails);
      return ResponseEntity.ok("Usuario actualizado exitosamente.");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el usuario.");
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable final int id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.ok("Usuario eliminado exitosamente.");
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el usuario.");
    }
  }

}

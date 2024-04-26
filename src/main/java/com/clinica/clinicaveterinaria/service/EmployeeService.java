package com.clinica.clinicaveterinaria.service;

import com.clinica.clinicaveterinaria.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines methods for managing users in the system.
 */
public interface EmployeeService {

  /**
   * Creates a new user in the system.
   *
   * @param employee The user information to create.
   * @return The created user.
   */
  Employee createUser(Employee employee);

  /**
   * Retrieves a user by their ID.
   *
   * @param id The ID of the user to retrieve.
   * @return An Optional containing the user if found, or an empty Optional if
   * no user is found.
   */
  Optional<Employee> getUserById(int id);

  /**
   * Retrieves all users from the system.
   *
   * @return A list of all users.
   */
  List<Employee> getAllUsers();

  /**
   * Updates the information of an existing user.
   *
   * @param id   The ID of the user to update.
   * @param employee The new information for the user.
   * @return The updated user.
   */
  Employee updateUser(int id, Employee employee);

  /**
   * Deletes a user from the system based on their ID.
   *
   * @param id The ID of the user to delete.
   */
  void deleteUser(int id);

  /**
   * Validates user login credentials.
   *
   * @param login    The login identifier of the user.
   * @param password The password of the user.
   * @return An Optional containing the user if valid credentials, or an empty
   * Optional if invalid.
   */
  Optional<Employee> validateUserLogin(String login, String password);

  /**
   * Checks if a user exists in the system by their ID.
   *
   * @param id The ID of the user to check.
   * @return true if the user exists, false otherwise.
   */
  boolean existsById(int id);
}


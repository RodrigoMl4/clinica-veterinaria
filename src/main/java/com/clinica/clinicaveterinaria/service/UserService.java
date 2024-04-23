package com.clinica.clinicaveterinaria.service;

import com.clinica.clinicaveterinaria.model.User;
import java.util.List;
import java.util.Optional;

/**
 * This interface defines methods for managing users in the system.
 */
public interface UserService {

  /**
   * Creates a new user in the system.
   *
   * @param user The user information to create.
   * @return The created user.
   */
  User createUser(User user);

  /**
   * Retrieves a user by their ID.
   *
   * @param id The ID of the user to retrieve.
   * @return An Optional containing the user if found, or an empty Optional if
   * no user is found.
   */
  Optional<User> getUserById(int id);

  /**
   * Retrieves all users from the system.
   *
   * @return A list of all users.
   */
  List<User> getAllUsers();

  /**
   * Updates the information of an existing user.
   *
   * @param id   The ID of the user to update.
   * @param user The new information for the user.
   * @return The updated user.
   */
  User updateUser(int id, User user);

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
  Optional<User> validateUserLogin(String login, String password);

  /**
   * Checks if a user exists in the system by their ID.
   *
   * @param id The ID of the user to check.
   * @return true if the user exists, false otherwise.
   */
  boolean existsById(int id);
}


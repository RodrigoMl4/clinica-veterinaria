package com.clinica.clinicaveterinaria.service.impl;


import com.clinica.clinicaveterinaria.model.User;
import com.clinica.clinicaveterinaria.repository.UserRepository;
import com.clinica.clinicaveterinaria.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
      this.userRepository = userRepository;
  }
  @Override
  @Transactional
  public User createUser(final User user) {
    return userRepository.save(user);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> getUserById(final int id) {
    return userRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public User updateUser(final int id, final User userDetails) {
    User user = userRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("User not found with id " + id));

    user.setName(userDetails.getName());
    user.setPhone(userDetails.getPhone());

    return userRepository.save(user);
  }

  @Override
  @Transactional
  public void deleteUser(final int id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> validateUserLogin(final String email,
      final String password) {
    User user = userRepository.findByEmail(email);
    if (user != null && user.getPassword().equals(password)) {
      return Optional.of(user);
    }
    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsById(final int id) {
    return userRepository.existsById(id);
  }

}

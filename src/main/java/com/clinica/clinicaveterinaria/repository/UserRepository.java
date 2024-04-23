package com.clinica.clinicaveterinaria.repository;


import com.clinica.clinicaveterinaria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);
}

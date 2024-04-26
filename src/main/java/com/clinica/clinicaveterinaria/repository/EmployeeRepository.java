package com.clinica.clinicaveterinaria.repository;


import com.clinica.clinicaveterinaria.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
  Employee findByEmail(String email);
}

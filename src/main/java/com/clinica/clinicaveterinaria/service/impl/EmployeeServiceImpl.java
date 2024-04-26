package com.clinica.clinicaveterinaria.service.impl;


import com.clinica.clinicaveterinaria.model.Employee;
import com.clinica.clinicaveterinaria.repository.EmployeeRepository;
import com.clinica.clinicaveterinaria.service.EmployeeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  @Transactional
  public Employee createUser(final Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Employee> getUserById(final int id) {
    return employeeRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Employee> getAllUsers() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional
  public Employee updateUser(final int id, final Employee employeeDetails) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(
            () -> new RuntimeException("User not found with id " + id));

    employee.setName(employeeDetails.getName());
    employee.setPhone(employeeDetails.getPhone());

    return employeeRepository.save(employee);
  }

  @Override
  @Transactional
  public void deleteUser(final int id) {
    employeeRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Employee> validateUserLogin(final String email,
      final String password) {
    Employee employee = employeeRepository.findByEmail(email);
    if (employee != null && employee.getPassword().equals(password)) {
      return Optional.of(employee);
    }
    return Optional.empty();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsById(final int id) {
    return employeeRepository.existsById(id);
  }

}

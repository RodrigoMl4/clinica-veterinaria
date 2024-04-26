package com.clinica.clinicaveterinaria.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(unique = true, length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String phone;

    @Column(unique = true, length = 50, nullable = false)
    private String ci;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 100, nullable = false)
    private String password;
}
